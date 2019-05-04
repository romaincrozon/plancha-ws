package com.theia.imdb;

import org.json.simple.JSONObject;

public interface OMDbWS {

	static String OMDB_API = "http://www.omdbapi.com/";
	static String API_KEY = "8d4c4f52";
	
	public JSONObject getMediaFromTitre(String title);
	
	public JSONObject getMediaFromId(String id);
	
	
}
