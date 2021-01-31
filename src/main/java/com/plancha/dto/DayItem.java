package com.plancha.dto;

import java.util.Calendar;

import com.plancha.dto.entity.CalendarItem;
import com.plancha.dto.entity.ResourceCalendar;

public class DayItem {

	private Long id;

	private Calendar calendar;
	private float value;
	private Long resourceCalendarId;

	public DayItem(Long id, Calendar calendar, float value, Long resourceCalendarId) {
		this.id = id;
		this.calendar = calendar;
		this.value = value;
		this.resourceCalendarId = resourceCalendarId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public CalendarItem toCalendarItem(ResourceCalendar resourceCalendar) {
		CalendarItem calendarItem = new CalendarItem();
		calendarItem.setCalendar(this.calendar);
		calendarItem.setResourceCalendar(resourceCalendar);
		calendarItem.setValue(this.value);
		calendarItem.setId(this.id);
		return calendarItem;
	}

	public Long getResourceCalendarId() {
		return resourceCalendarId;
	}

	public void setResourceCalendarId(Long resourceCalendarId) {
		this.resourceCalendarId = resourceCalendarId;
	}
}
