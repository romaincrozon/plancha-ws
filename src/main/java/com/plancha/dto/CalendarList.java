package com.plancha.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.plancha.utils.CalendarUtils;

public class CalendarList {

    private List<PlanchaCalendar> planchaCalendarList = new ArrayList<PlanchaCalendar>();
	private List<PlanchaMonth> monthList = new ArrayList<PlanchaMonth>();
	private List<PlanchaWeek> weekList = new ArrayList<PlanchaWeek>();

	public CalendarList(CalendarRange calendarRange) {

 		Calendar begin = CalendarUtils.stringToDate(calendarRange.getStartDate());
		Calendar end = CalendarUtils.stringToDate(calendarRange.getEndDate());
		
		if (begin != null && end != null) {
			if (!begin.before(end)) {
				System.out.println("End date before begin date");
			}
	
			this.planchaCalendarList.add(new PlanchaCalendar(CalendarUtils.createCalendar(begin)));
			int currentMonth = begin.get(Calendar.MONTH);
			int currentWeek = begin.get(Calendar.WEEK_OF_YEAR);
			int numberOfDaysInMonth = 1;
			int numberOfDaysInWeek = 1;
			
			while (!begin.getTime().equals(end.getTime())) {
				Calendar newCalendar = CalendarUtils.createCalendar(begin);
				CalendarUtils.incrementeCalendarOneDay(newCalendar);
				CalendarUtils.incrementeCalendarOneDay(begin);
				if (newCalendar.get(Calendar.MONTH) != currentMonth) {
					this.monthList.add(new PlanchaMonth(currentMonth, CalendarUtils.getMonth(currentMonth), numberOfDaysInMonth));
					currentMonth = newCalendar.get(Calendar.MONTH);
					numberOfDaysInMonth = 0;
				}
				if (newCalendar.get(Calendar.WEEK_OF_YEAR) != currentWeek) {
					this.weekList.add(new PlanchaWeek(currentWeek, numberOfDaysInWeek));
					currentWeek = newCalendar.get(Calendar.WEEK_OF_YEAR);
					
					numberOfDaysInWeek = 0;
				}
				numberOfDaysInMonth++;
	//			if (CalendarUtils.isWeekDay(newCalendar)){
					this.planchaCalendarList.add(new PlanchaCalendar(newCalendar));
					numberOfDaysInWeek++;
	//			}
			}
			if (numberOfDaysInMonth > 0)
				this.monthList.add(new PlanchaMonth(currentMonth, CalendarUtils.getMonth(currentMonth), numberOfDaysInMonth));
			if (numberOfDaysInWeek > 0)
				this.weekList.add(new PlanchaWeek(currentWeek, numberOfDaysInWeek));
		}
	}
	
	public List<PlanchaCalendar> getPlanchaCalendarList() {
		return planchaCalendarList;
	}
	public void setPlanchaCalendarList(List<PlanchaCalendar> planchaCalendarList) {
		this.planchaCalendarList = planchaCalendarList;
	}

	public List<PlanchaMonth> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<PlanchaMonth> monthList) {
		this.monthList = monthList;
	}

	public List<PlanchaWeek> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<PlanchaWeek> weekList) {
		this.weekList = weekList;
	}
}
