package com.theia.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.util.StringUtils;

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

	public static String getSQLDate(String date) {
		if (!isValueNull(date)) {
			SimpleDateFormat dateFormatIn = new SimpleDateFormat("dd MMM yyyy", new Locale("en,EN"));
			SimpleDateFormat dateFormatOut = new SimpleDateFormat("yyyy-MM-dd", new Locale("en,EN"));
			try {
				Date parsedDate = dateFormatIn.parse(date);
				return dateFormatOut.format(parsedDate); 
			} catch (ParseException e) {
				System.out.println("Unparsable date : " + date);
			}
		}
		return null;
	}
	
	public static boolean isValueNull(String value) {
		return StringUtils.isEmpty(value) || "N/A".equals(value);
	}
}
