package com.theia.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.util.CollectionUtils;

import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.media.beans.Episode;
import com.theia.models.media.beans.Film;
import com.theia.models.media.beans.Media;
import com.theia.models.media.beans.Person;
import com.theia.models.media.beans.Rating;
import com.theia.models.media.beans.Saison;
import com.theia.models.media.beans.Serie;
import com.theia.utils.MediaUtils;
import com.theia.utils.Utils;

public class DAOMedia {

	public static String SERIE_QUERY = "SELECT * FROM theia_db.series AS series "
			+ "INNER JOIN theia_db.episodes as episodes ON series.serie_id = episodes.episode_seriesID ";
	
	public static Media getMediaById(String id, TypeMedia typeMedia) {
    	if(typeMedia == TypeMedia.serie) {
    		return getSerieById(id);
    	}
    	return getFilmById(id);

	}
	
	
	private static Media getFilmById(String id) {
    	Map<String, String> queryParameters = new HashMap<String, String>();
    	queryParameters.put(Fields.ID.toString(), id);

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
    	queryParameters.put(Fields.SERIE + Fields.ID.toString(), id);
		try {
			ResultSet resultSet = DAOUtils.prepareSelectInnerJoinSeriesQuery(DAOMedia.SERIE_QUERY, queryParameters, Fields.EPISODE + Fields.NUM_SAISON);
			if (resultSet != null) {
	            return MediaUtils.buildCompleteSerie(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

	public static List<Media> getAllMedias() {
		List<Media> medias = new ArrayList<Media>();
		medias.addAll(getAllMediaByType(TypeMedia.film));
		medias.addAll(getAllMediaByType(TypeMedia.serie));
		
		return medias;
    }


	public static List<Media> getTopMediaByType(TypeMedia typeMedia) {
		try {
			Map<String, String> queryParameters = new HashMap<String, String>();
			queryParameters.put(Fields.IMDBRATING.toString() + ">", TheiaConfiguration.getAppConfigInstance().getTopMinValue());
			ResultSet resultSet = DAOUtils.prepareSelectAndQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(typeMedia), queryParameters);
			return MediaUtils.buildMediaListFromResultSet(typeMedia, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static void insertPersonsList(List<Person> persons, int mediaDbId) {
//		if (!CollectionUtils.isEmpty(persons)){
//			persons.stream().map(person -> {
//				try {
//					int personDbId = DAOUtils.prepareInsertQuery("persons", person.getAttributesMap());
//					Map<String, String> mediaPersonMap = new HashMap<String, String>();
//					mediaPersonMap.put("media_id", String.valueOf(mediaDbId));
//					mediaPersonMap.put("person_id", String.valueOf(personDbId));
//					return DAOUtils.prepareInsertQuery("media_person", mediaPersonMap);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				return -1;
//			});
//		}
//	}
	
//	public static void insertRatingsList(List<Rating> ratings, int mediaDbId) {
//		if (!CollectionUtils.isEmpty(ratings)){
//			ratings.stream().map(rating -> {
//				try {
//					int ratingDbId = DAOUtils.prepareInsertQuery("ratings", rating.getAttributesMap());
//					Map<String, String> mediaRatingsMap = new HashMap<String, String>();
//					mediaRatingsMap.put("media_id", String.valueOf(mediaDbId));
//					mediaRatingsMap.put("rating_id", String.valueOf(ratingDbId));
//					return DAOUtils.prepareInsertQuery("media_rating", mediaRatingsMap);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				return -1;
//			});
//		}
//	}
	
	public static void insertMedia(Media media) {
		try {
			Map<String, String> queryParameters = media.getAttributesMap();
			boolean mediaExists = DAOMedia.isMediaExistsInDatabase(media);
			if (!mediaExists) {
				DAOUtils.prepareInsertQuery(media.getDatabaseTable(), queryParameters);
	//			insertPersonsList(media.getActors(), mediaDbId);
	//			insertRatingsList(media.getRatings(), mediaDbId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

	public static boolean isMediaExistsInDatabase(Media media) {
		Map<String, String> queryParameters = new HashMap<String, String>();
		if (media instanceof Serie || media instanceof Film) {
			queryParameters.put(Fields.ID.toString(), String.valueOf(media.getImdbID()));
		}else if (media instanceof Saison){
			queryParameters.put(Fields.SERIE_ID.toString(), String.valueOf(((Saison) media).getIdSerie()));
			queryParameters.put(Fields.NUM_SAISON.toString(), String.valueOf(((Saison) media).getSeason()));
		}else if (media instanceof Episode){
			queryParameters.put(Fields.SERIE_ID.toString(), String.valueOf(((Episode) media).getIdSerie()));
			queryParameters.put(Fields.NUM_SAISON.toString(), String.valueOf(((Episode) media).getNumSaison()));
			queryParameters.put(Fields.NUM_EPISODE.toString(), String.valueOf(((Episode) media).getNumEpisode()));
		}
		if (!CollectionUtils.isEmpty(queryParameters))
			return DAOUtils.isEntryExists(media.getDatabaseTable(), queryParameters);
		return false;
	}
}
