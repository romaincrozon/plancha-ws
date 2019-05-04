package com.theia.imdb;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.theia.httpConnection.HttpRequest;

public class OMDbWSImpl implements OMDbWS{

	@Override
	public JSONObject getMediaFromTitre(String title) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("apikey", API_KEY);
		parameters.put("t", title);
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
