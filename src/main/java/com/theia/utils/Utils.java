package com.theia.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static Object mapJSONToDTO(JSONObject json, Class dtoClass) {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
		try {
			return mapper.readValue(json.toString(), dtoClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> replaceValuesInMap(Map<String, String> map){
		return map.values().stream().map(value -> value.replace("'", "''")).collect(Collectors.toList());
	}

}
