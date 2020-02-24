package com.theia.models.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.theia.config.DatabaseConfig;
import com.theia.config.DatabaseTable;
import com.theia.config.DatabaseTables;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.media.beans.Episode;
import com.theia.models.media.beans.Film;
import com.theia.models.media.beans.Media;
import com.theia.models.media.beans.MissingMedia;
import com.theia.models.media.beans.Saison;
import com.theia.models.media.beans.Serie;
import com.theia.utils.MediaUtils;

public class DAOMedia {

	public static String SERIE_QUERY = "SELECT * FROM theia_db.series AS series "
			+ "INNER JOIN theia_db.episodes as episodes ON series.serie_id = episodes.episode_seriesID ";
//	public static String[] alphabet = {"A", "B", "C", D E F G H I J K L M N O P Q R S T UV W X Y Z};
	
	
	public static Media getMediaById(String id, TypeMedia typeMedia) {
    	if(typeMedia == TypeMedia.serie) {
    		return getSerieById(id);
    	}
    	return getFilmById(id);

	}
	
	private static Media getFilmById(String id) {
    	Map<String, String> queryParameters = new HashMap<String, String>();
    	queryParameters.put(Fields.FILM_PREFIX + Fields.ID, id);

		try {
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film), queryParameters);
			if (resultSet.next()) {
	            return new Film(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	private static Media getSerieById(String id) {
		Map<String, String> queryParameters = new HashMap<String, String>();
    	queryParameters.put(Fields.SERIE_PREFIX + Fields.ID, id);
		try {
			ResultSet resultSet = DAOUtils.prepareSelectInnerJoinSeriesQuery(DAOMedia.SERIE_QUERY, queryParameters, Fields.EPISODE_PREFIX + Fields.NUM_SAISON);
			if (resultSet != null) {
	            return MediaUtils.buildCompleteSerie(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	private static Media getEpisodeById(String id) {
		Map<String, String> queryParameters = new HashMap<String, String>();
		DatabaseTable table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
    	queryParameters.put(Fields.EPISODE_PREFIX + Fields.ID, id);
		try {
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(table, queryParameters);
			if (resultSet != null) {
	            return MediaUtils.buildEpisode(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Media getMediaById(String id) {
    	Media media = getEpisodeById(id);
    	return media != null ? media : getFilmById(id);
	}

	public static List<Media> getAllMediaByType(TypeMedia typeMedia) {
		try {
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia));
			return MediaUtils.buildMediaListFromResultSet(typeMedia, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public static Map<String, List<Media>> getAllMediaByTypeAlphabetical(TypeMedia typeMedia) {
		try {
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia));
			List<Media> medias = MediaUtils.buildMediaListFromResultSet(typeMedia, resultSet);
			Map<String, List<Media>> mediasMap = new HashMap<String, List<Media>>();
			for(Media media : medias) {
				String firstLetter = media.getTitle().substring(0, 1).toUpperCase();
				char firstChar = media.getTitle().toUpperCase().charAt(0);
				if (!Character.isLetter(firstChar)) {
					firstLetter = "1-9";
				}
				if (mediasMap.containsKey(firstLetter)) {
					mediasMap.get(firstLetter).add(media);
				}else {
					List<Media> mediasList = new ArrayList<Media>();
					mediasList.add(media);
					mediasMap.put(firstLetter, mediasList);
				}
			}
			return mediasMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

	public static List<Media> getAllMedias() {
		List<Media> medias = new ArrayList<Media>();
		medias.addAll(getAllMediaByType(TypeMedia.film));
		medias.addAll(getAllMediaByType(TypeMedia.serie));
		
		return medias;
    }


	public static List<Media> getTopMediaByType(TypeMedia typeMedia) {
		try {
			Map<String, String> queryParameters = new HashMap<String, String>();
			queryParameters.put(Fields.SERIE_PREFIX + Fields.IMDBRATING.toString() + ">", TheiaConfiguration.getAppConfigInstance().getTopMinValue());
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia), queryParameters);
			return MediaUtils.buildMediaListFromResultSet(typeMedia, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void insertMedia(Media media) {
		try {
			Map<String, String> queryParameters = media.getAttributesMap();
			boolean mediaExists = DAOMedia.isMediaExistsInDatabase(media);
			if (!mediaExists) {
				DAOUtils.prepareInsertQuery(media.getDatabaseTable(), queryParameters);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public static void insertMissingMedia(MissingMedia missingMedia) {
		try {
			Map<String, String> queryParameters = missingMedia.getAttributesMap();
			boolean mediaExists = DAOMedia.isMissingMediaExistsInDatabase(missingMedia);
			if (!mediaExists) {
				DAOUtils.prepareInsertQuery(missingMedia.getTable(), queryParameters);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

	public static boolean isMediaExistsInDatabase(Media media) {
		Map<String, String> queryParameters = new HashMap<String, String>();
		String table = media.getDatabaseTable().getType().name() + "_";
		if (media instanceof Serie || media instanceof Film) {
			queryParameters.put(table + Fields.ID, String.valueOf(media.getImdbID()));
		}else if (media instanceof Saison){
			queryParameters.put(table + Fields.SERIE_ID, String.valueOf(((Saison) media).getIdSerie()));
			queryParameters.put(table + Fields.NUM_SAISON, String.valueOf(((Saison) media).getSeason()));
		}else if (media instanceof Episode){
			queryParameters.put(table + Fields.SERIE_ID, String.valueOf(((Episode) media).getIdSerie()));
			queryParameters.put(table + Fields.NUM_SAISON, String.valueOf(((Episode) media).getNumSaison()));
			queryParameters.put(table + Fields.NUM_EPISODE, String.valueOf(((Episode) media).getNumEpisode()));
		}
		if (!CollectionUtils.isEmpty(queryParameters))
			return DAOUtils.isEntryExists(media.getDatabaseTable(), queryParameters);
		return false;
	}

	public static boolean isMissingMediaExistsInDatabase(MissingMedia missingMedia) {
		Map<String, String> queryParameters = new HashMap<String, String>();
		queryParameters.put(Fields.MISSINGMEDIA_PREFIX + Fields.TITRE, missingMedia.getTitle());
		return DAOUtils.isEntryExists(missingMedia.getTable(), queryParameters);
	}

	public static boolean isMediaExistsInDatabase(TypeMedia typeMedia, String path) {
		Map<String, String> queryParameters = new HashMap<String, String>();
		String table = typeMedia.name() + "_";
		queryParameters.put(table + Fields.URL, path);
		
		return DAOUtils.isEntryExists(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia), queryParameters);
	}

	public static List<String> getAllGenres(TypeMedia typeMedia) {
		try {
			List<String> genres = new ArrayList<String>();
			ResultSet resultSet = DAOUtils.prepareSelectSpecificField(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia), Fields.SERIE_PREFIX + Fields.GENRE);
			while(resultSet.next()) {
				String[] genresArray = DAOUtils.getFieldStringValue(resultSet, typeMedia.name() + "_" + Fields.GENRE).split(",");
				for(String genre : genresArray) {
					if (!genres.contains(genre.trim())) {
						genres.add(genre.trim());
					}
				}
			}
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Media> getRelatedFilms(String genres) {
		try {
			Map<String, List<String>> queryParameters = new HashMap<String, List<String>>();
			List<String> genresList = Arrays.asList(genres.split(","));
			queryParameters.put(Fields.FILM_PREFIX + Fields.GENRE, genresList);
			ResultSet resultSet = DAOUtils.prepareSelectOrQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film), queryParameters);
			return MediaUtils.buildMediaListFromResultSet(TypeMedia.film, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
