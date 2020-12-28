package com.plancha.dto;

import java.util.Calendar;

public class NgbDate {

	private int year;
	private int month;
	private int day;
	
	public NgbDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day);
		return calendar;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
}
