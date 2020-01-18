package com.theia.imdb;

import org.json.JSONObject;

import com.theia.enums.TypeMedia;

public interface OMDbWS {

	static String OMDB_API = "http://www.omdbapi.com/";
	static String API_KEY = "8d4c4f52";
	
	public JSONObject getSaison(int serieId, int numSaison);
	
	public JSONObject getEpisode(int serieId, int numSaison, int numEpisode);
	
	public JSONObject getMediaFromId(String id);

	public JSONObject getFilmFromTitre(String title);

	public JSONObject getSerieFromTitre(String title);
	
	
}
