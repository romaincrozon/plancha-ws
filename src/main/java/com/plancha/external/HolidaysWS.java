package com.plancha.external;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.plancha.httpConnection.HttpRequest;

public class HolidaysWS {

	public static String HOLIDAYS_API = "https://getfestivo.com/v2/holidays";
	public static String API_KEY = "dfd7b1af13ed71dc5a1d1d923f9cddb3";
	public static String DEFAULT_COUNTRY = "fr";

	public static String API_KEY_PARAM = "api_key";
	public static String COUNTRY_PARAM = "country";
	public static String YEAR_PARAM = "year";
	
	public static HolidaysWS instance;
	
	public static HolidaysWS getInstance() {
		if (instance == null) {
			instance = new HolidaysWS();
		}
		return instance;
	}
	
	public JSONObject getHolidaysByYear(String year) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("api_key", API_KEY);
		parameters.put("country", DEFAULT_COUNTRY);
		parameters.put("year", year);
		return HttpRequest.request(HOLIDAYS_API, parameters);
	}
	
	public JSONObject getHolidaysByDate(Calendar calendar) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("api_key", API_KEY);
		parameters.put("country", DEFAULT_COUNTRY);
		parameters.put("year", "" + calendar.get(Calendar.YEAR));
		parameters.put("month", "" + calendar.get(Calendar.MONTH) + 1);
		parameters.put("day", "" + calendar.get(Calendar.DAY_OF_MONTH));
		return HttpRequest.request(HOLIDAYS_API, parameters);
	}
}
