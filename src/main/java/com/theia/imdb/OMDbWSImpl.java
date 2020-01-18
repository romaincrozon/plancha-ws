package com.theia.imdb;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.theia.enums.TypeMedia;
import com.theia.httpConnection.HttpRequest;

public class OMDbWSImpl implements OMDbWS{

	@Override
	public JSONObject getSerieFromTitre(String title) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", API_KEY);
		parameters.put("t", title);
		parameters.put("type", "serie");
		return HttpRequest.request(OMDB_API, parameters);
	}
	
	@Override
	public JSONObject getFilmFromTitre(String title) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", API_KEY);
		parameters.put("t", title);
		parameters.put("type", "film");
		return HttpRequest.request(OMDB_API, parameters);
	}


	@Override
	public JSONObject getSaison(int serieId, int numSaison) {
		Map<String, String> parameters = OMDbUtils.getGlobalParametersById(API_KEY, serieId);
		parameters.put("season", "" + numSaison);
		return HttpRequest.request(OMDB_API, parameters);
	}
	
	@Override
	public JSONObject getEpisode(int serieId, int numSaison, int numEpisode) {
		Map<String, String> parameters = OMDbUtils.getGlobalParametersById(API_KEY, serieId);
		parameters.put("season", "" + numSaison);
		parameters.put("episode", "" + numEpisode);
		return HttpRequest.request(OMDB_API, parameters);
	}

	@Override
	public JSONObject getMediaFromId(String id) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", API_KEY);
		parameters.put("i", id);
		return HttpRequest.request(OMDB_API, parameters);
	}
}
