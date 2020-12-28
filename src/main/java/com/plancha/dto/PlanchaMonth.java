package com.plancha.dto;

public class PlanchaMonth {
	
	private int numMonth;
	private String name;
	private int numberOfDays;

	public PlanchaMonth(int numMonth, String name, int numberOfDays ) {
		this.numMonth = numMonth;
		this.numberOfDays = numberOfDays;
		this.name = name;
	}

	public int getNumMonth() {
		return numMonth;
	}

	public void setNumMonth(int numMonth) {
		this.numMonth = numMonth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
}