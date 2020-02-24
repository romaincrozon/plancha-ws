package com.theia.imdb;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.theia.enums.TypeMedia;
import com.theia.models.media.beans.Media;
import com.theia.models.media.beans.Saison;
import com.theia.models.media.beans.Serie;
import com.theia.utils.Utils;

public class OMDbUtils {

	public static Object getOmdbSerie(String title, Class dtoClass) {
		OMDbWS omdbWS = new OMDbWSImpl();
		JSONObject mediaJSON = omdbWS.getSerieFromTitre(title);
		if (mediaJSON == null || (mediaJSON.has("Response") && mediaJSON.get("Response").equals("False"))) {
			System.out.println("Error retreiving Serie " + title);
			return null;
		}
		return Utils.mapJSONToDTO(mediaJSON, dtoClass);
	}
	
	public static Object getOmdbFilm(String title, Class dtoClass) {
		OMDbWS omdbWS = new OMDbWSImpl();
		JSONObject mediaJSON = omdbWS.getFilmFromTitre(title);
		if (mediaJSON == null || (mediaJSON.has("Response") && mediaJSON.get("Response").equals("False"))) {
			System.out.println("Error retreiving Film " + title);
			return null;
		}
		return Utils.mapJSONToDTO(mediaJSON, dtoClass);
	}

	public static Object getOmdbSaison(Class dtoClass, String serieId, int numSaison) {
		OMDbWS omdbWS = new OMDbWSImpl();
		JSONObject mediaJSON = omdbWS.getSaison(serieId, numSaison);
		if (mediaJSON == null || (mediaJSON.has("Response") && mediaJSON.get("Response").equals("False"))) {
			System.out.println("Error retreiving Saison " + numSaison + " from serie id " + serieId);
			return null;
		}
		return Utils.mapJSONToDTO(mediaJSON, dtoClass);
	}
	
	public static Object getOmdbEpisode(Class dtoClass, String serieId, int numSaison, int numEpisode) {
		OMDbWS omdbWS = new OMDbWSImpl();
		JSONObject mediaJSON = omdbWS.getEpisode(serieId, numSaison, numEpisode);
		if (mediaJSON == null || (mediaJSON.has("Response") && mediaJSON.get("Response").equals("False"))) {
			System.out.println("Error retreiving Episode " + numEpisode + " from serie id " + serieId + " and saison " + numEpisode + ".");
			return null;
		}
		return Utils.mapJSONToDTO(mediaJSON, dtoClass);
	}
	
	public static Map<String, String> getGlobalParametersByTitle(String apiKey, String title){
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", apiKey);
		parameters.put("t", title); 
		return parameters;
	}
	
	public static Map<String, String> getGlobalParametersById(String apiKey, String id){
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", apiKey);
		parameters.put("i", "" + id);
		return parameters;
	}
	
}
