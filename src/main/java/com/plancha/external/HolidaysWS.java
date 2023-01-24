package com.plancha.external;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.plancha.httpConnection.HttpRequest;

public class HolidaysWS {

	public static String HOLIDAYS_API = "https://calendrier.api.gouv.fr/jours-feries/";
	public static String JSON_EXT = ".json";
	public static String ZONE = "metropole";
	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static HolidaysWS instance;
	public Map<Integer, JSONObject> holidaysPerYear;
	
	public static HolidaysWS getInstance() {
		if (instance == null) {
			instance = new HolidaysWS();
			instance.setHolidaysPerYear(new HashMap<Integer, JSONObject>());
		}
		return instance;
	}
	
	public JSONObject getHolidaysByYear(int year) {
		if (!this.holidaysPerYear.containsKey(year) || this.holidaysPerYear.get(year) == null) {
			this.holidaysPerYear.put(year, HttpRequest.request(HOLIDAYS_API + ZONE + "/" + year + JSON_EXT, null));
		}
		return this.holidaysPerYear.get(year);
	}
	
	public boolean isHoliday(Calendar calendar) {
		SimpleDateFormat format1 = new SimpleDateFormat(DATE_FORMAT);
		return getHolidaysByYear(calendar.get(Calendar.YEAR)).has(format1.format(calendar.getTime()));
	}

	public Map<Integer, JSONObject> getHolidaysPerYear() {
		return holidaysPerYear;
	}

	public void setHolidaysPerYear(Map<Integer, JSONObject> holidaysPerYear) {
		this.holidaysPerYear = holidaysPerYear;
	}
}
