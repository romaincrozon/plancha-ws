package com.plancha.dto;

import java.util.List;

public class PlanchaWeek {
	
	private int numWeek;
	private int numberOfDays;
	private int year;
	private List<PlanchaCalendar> planchaCalendars;

	public PlanchaWeek(int year, int numWeek, int numberOfDays, List<PlanchaCalendar> planchaCalendars) {
		this.year = year;
		this.numWeek = numWeek;
		this.numberOfDays = numberOfDays;
		this.planchaCalendars = planchaCalendars;
	}

	public int getNumWeek() {
		return numWeek;
	}

	public void setNumWeek(int numWeek) {
		this.numWeek = numWeek;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public List<PlanchaCalendar> getPlanchaCalendars() {
		return planchaCalendars;
	}

	public void setPlanchaCalendars(List<PlanchaCalendar> planchaCalendars) {
		this.planchaCalendars = planchaCalendars;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}	
}