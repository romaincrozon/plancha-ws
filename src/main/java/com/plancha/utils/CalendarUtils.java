package com.plancha.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.plancha.dto.CalendarList;
import com.plancha.dto.Holidays;
import com.plancha.dto.PlanchaCalendar;
import com.plancha.dto.entity.CalendarItem;
import com.plancha.external.HolidaysWS;

public class CalendarUtils {

	public static Calendar createCalendar(Calendar calendar) {
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(calendar.getTime());
		return newCalendar;
	}
	
	public static void incrementeCalendarOneDay(Calendar calendar) {
		calendar.add(Calendar.DATE, 1);
	}

	public static boolean isWeekDay(Calendar calendar) {
		return !(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
			    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

	public static String getMonth(int month) {
		HashMap<Integer, String> mapMonth = new HashMap<Integer, String>();
        
        mapMonth.put(0, "Janvier");
        mapMonth.put(1, "Février");
        mapMonth.put(2, "Mars");
        mapMonth.put(3, "Avril");
        mapMonth.put(4, "Mai");
        mapMonth.put(5, "Juin");
        mapMonth.put(6, "Juillet");
        mapMonth.put(7, "Août");
        mapMonth.put(8, "Septembre");
        mapMonth.put(9, "Octobre");
        mapMonth.put(10, "Novembre");
        mapMonth.put(11, "Décembre");
		
        return mapMonth.get(month);
	}
	
	public static boolean isHoliday(Calendar calendar) {
		JSONObject holidaysJSONObject = HolidaysWS.getInstance().getHolidaysByDate(calendar);
		Holidays holidays = (Holidays) Utils.mapJSONToDTO(holidaysJSONObject, Holidays.class);
		return (holidays != null && !holidays.getHolidays().isEmpty());
	}
}
