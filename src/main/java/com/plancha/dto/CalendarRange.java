package com.plancha.dto;

import java.util.Calendar;

import com.plancha.utils.CalendarUtils;

public class CalendarRange {

    private Calendar startDate;
	private Calendar endDate;
	
	public CalendarRange() {}
	
	public CalendarRange(int nbWeeks, Calendar startDate) {
		if (startDate == null) {
			startDate = Calendar.getInstance();
		}
		this.startDate = CalendarUtils.getFirstDayOfTheWeek(startDate);
		this.endDate = CalendarUtils.addWeeks(this.startDate, nbWeeks);
	}
	
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
}