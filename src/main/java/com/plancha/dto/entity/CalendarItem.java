package com.plancha.dto.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "calendarItem")
public class CalendarItem {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private long id; 

    private Calendar calendar;
    private boolean isHoliday;
    private float value;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value="resourceCalendar-calendarItem")
	private ResourceCalendar resourceCalendar; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ResourceCalendar getResourceCalendar() {
		return resourceCalendar;
	}
	public void setResourceCalendar(ResourceCalendar resourceCalendar) {
		this.resourceCalendar = resourceCalendar;
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
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}