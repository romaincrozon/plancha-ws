package com.plancha.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static Object mapJSONToDTO(JSONObject json, Class dtoClass) {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			return mapper.readValue(json.toString(), dtoClass);
		} catch (Exception e) {
			logger.error("Error parsing JSON for class:" + dtoClass.getName());
		}
		return null;
	}

	public static List<String> replaceValuesInMap(Map<String, String> map){
		return map.values().stream().map(value -> value.replace("'", "''")).collect(Collectors.toList());
	}
}
