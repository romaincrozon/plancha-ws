package com.plancha.dto;

public class PlanchaWeek {
	
	private int numWeek;
	private int numberOfDays;

	public PlanchaWeek(int numWeek, int numberOfDays ) {
		this.numWeek = numWeek;
		this.numberOfDays = numberOfDays;
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
}