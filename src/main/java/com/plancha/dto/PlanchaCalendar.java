package com.plancha.dto;

import java.util.Calendar;

import com.plancha.utils.CalendarUtils;

public class PlanchaCalendar {
	
    private Calendar calendar; 
    private boolean isHoliday; 
    private int dayNumberInWeek;

	public PlanchaCalendar(Calendar calendar, int dayNumberInWeek) {
		this.calendar = calendar;
		this.dayNumberInWeek = dayNumberInWeek;
		this.isHoliday = !CalendarUtils.isWeekDay(this.calendar) /*|| CalendarUtils.isHoliday(this.calendar)*/ ;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	public int getDayNumberInWeek() {
		return dayNumberInWeek;
	}

	public void setDayNumberInWeek(int dayNumberInWeek) {
		this.dayNumberInWeek = dayNumberInWeek;
	}
}