package com.theia.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOMedia;
import com.theia.models.media.beans.Episode;
import com.theia.models.media.beans.Film;
import com.theia.models.media.beans.Media;
import com.theia.models.media.beans.Saison;
import com.theia.models.media.beans.Serie;

public class MediaUtils {

    public static Media buildMediaFromResultSet(ResultSet resultSet) {
		try {
			switch (resultSet.getString("type")) {
			case "episode":
				return buildFilmFromResultSet(resultSet);
			case "series":
				return buildSerieFromResultSet(resultSet);
			case "season":
				return buildSaisonFromResultSet(resultSet);
			case "movie":
				return buildFilmFromResultSet(resultSet);
			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static Media buildFilmFromResultSet(ResultSet resultSet) throws SQLException{
    	return buildMediaByType(TypeMedia.film, resultSet);
    }
    
    public static Media buildSerieFromResultSet(ResultSet resultSet) throws SQLException {
    	return buildMediaByType(TypeMedia.serie, resultSet);
    }
    
    public static Media buildSaisonFromResultSet(ResultSet resultSet) throws SQLException {
    	return buildMediaByType(TypeMedia.saison, resultSet);
    }
    
	public static List<Media> buildMediaListFromResultSet(TypeMedia typeMedia, ResultSet resultSet) {
		try {
			List<Media> medias = new ArrayList<Media>();
			while (resultSet.next()) {
				medias.add(buildMediaByType(typeMedia, resultSet));
			}
			return medias;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static Media buildMediaByType(TypeMedia typeMedia, ResultSet resultSet) {
    	switch (typeMedia) {
		case film:
			return new Film(resultSet);
		case serie:
			return new Serie(resultSet);
		case episode:
			return new Episode(resultSet);
		default:
			return null;
		}
    }
    
    public static JSONObject toJSON(Media media) {
    	try {
        	final ObjectMapper mapper = new ObjectMapper();
			return new JSONObject(mapper.writeValueAsString(media));
		} catch (JsonProcessingException | JSONException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static JSONObject toJSON(List<Media> medias) {
    	final JSONArray jsonArray = new JSONArray();
    	for(Media media : medias) {
    		jsonArray.put(toJSON(media));
    	}
    	return new JSONObject().put("medias", jsonArray);
    }

	public static List<Media> getRandomNumberOfMedias(List<Media> medias, int nbMedia){
        Random rand = new Random(); 
        List<Media> randomMediasList = new ArrayList<Media>();
//        if (medias.size() <= nbMedia) {
//        	return medias;
//        }
        for (int i = 0; i < nbMedia; i++) {
        	Media media = medias.get(rand.nextInt(medias.size()));
//        	if (randomMediasList.indexOf(media) < -1)
        		randomMediasList.add(media);
        }
        return randomMediasList; 
	}

	public static List<Media> getTopMediasByType(TypeMedia typeMedia) {
        return DAOMedia.getTopMediaByType(typeMedia);
	}
	
	public static Media getMediaById(int id, TypeMedia typeMedia) {
        return DAOMedia.getMediaById(id, typeMedia);
	}
}
