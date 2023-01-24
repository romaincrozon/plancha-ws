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
	public static boolean isFirstDayOfTheWeek(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
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
        
        mapMonth.put(0, "Jan");
        mapMonth.put(1, "Fév");
        mapMonth.put(2, "Mar");
        mapMonth.put(3, "Avr");
        mapMonth.put(4, "Mai");
        mapMonth.put(5, "Jui");
        mapMonth.put(6, "Jui");
        mapMonth.put(7, "Aoû");
        mapMonth.put(8, "Sep");
        mapMonth.put(9, "Oct");
        mapMonth.put(10, "Nov");
        mapMonth.put(11, "Déc");
		
        return mapMonth.get(month);
	}
	
	public static boolean isHoliday(Calendar calendar) {
		return HolidaysWS.getInstance().isHoliday(calendar);
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
