package com.plancha.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.plancha.dto.entity.CalendarItem;
import com.plancha.dto.entity.ResourceCalendar;

public class WeekItem {

	private List<Calendar> calendars;
	private float value;
	private Long resourceCalendarId;
	
	public WeekItem(List<Calendar> calendars, float value, Long resourceCalendarId) {
		this.calendars = calendars;
		this.value = value;
		this.resourceCalendarId = resourceCalendarId;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public List<CalendarItem> toCalendarItem(ResourceCalendar resourceCalendar) {
		List<CalendarItem> calendarItems = new ArrayList<CalendarItem>();
		for(Calendar calendar : calendars) {
			CalendarItem calendarItem = new CalendarItem();
			calendarItem.setCalendar(calendar);
			calendarItem.setValue(value / calendars.size());
			calendarItem.setResourceCalendar(resourceCalendar);
			calendarItems.add(calendarItem);
		}
		return calendarItems;
	}

	public List<Calendar> getCalendars() {
		return calendars;
	}

	public void setCalendars(List<Calendar> calendars) {
		this.calendars = calendars;
	}

	public Long getResourceCalendarId() {
		return resourceCalendarId;
	}

	public void setResourceCalendarId(Long resourceCalendarId) {
		this.resourceCalendarId = resourceCalendarId;
	}
}
