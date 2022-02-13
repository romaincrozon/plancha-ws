package com.plancha.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.plancha.dto.Holidays;
import com.plancha.external.HolidaysWS;

public class CalendarUtils {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final int OFFSET_HOUR = 1;
	private static final int OFFSET_DAY = 1;

	public static Calendar createCalendar(Calendar calendar) {
		return createCalendar(calendar, OFFSET_HOUR);
	}
	
	public static Calendar createCalendar(Calendar calendar, int offset) {
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(calendar.getTime());
		newCalendar.add(Calendar.HOUR, offset);
		return newCalendar;
	}
	
	public static void incrementeCalendarOneDay(Calendar calendar) {
		calendar.add(Calendar.DATE, OFFSET_DAY);
	}

	public static boolean isWeekDay(Calendar calendar) {
		return !(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
			    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}
	
	public static List<Calendar> getWeekFromCalendar(Calendar calendar) {
		List<Calendar> calendars = new ArrayList<Calendar>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		for(int i = Calendar.MONDAY; i <= Calendar.FRIDAY; i++) {
			Calendar c = createCalendar(calendar);
			c.set(Calendar.DAY_OF_WEEK, i);
			System.out.println(format1.format(c.getTime()));    
			calendars.add(c);
		}
		return calendars;
	}
	
	public static Calendar getFirstDayOfTheWeek(Calendar calendar) {
		Calendar c = createCalendar(calendar, 0);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c;
	}
	
	public static Calendar addWeeks(Calendar calendar, int nbWeeks) {
		Calendar c = createCalendar(calendar, 0);
		c.add(Calendar.DATE, nbWeeks * 7 - 1);
		return c;
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
	
	public static Calendar stringToDate(String date) {
		if (date != null) {
			try {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				calendar.setTime(sdf.parse(date));
				calendar.add(Calendar.HOUR, OFFSET_HOUR);
				return calendar;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
