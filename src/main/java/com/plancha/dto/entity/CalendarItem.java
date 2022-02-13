package com.plancha.dto.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "calendarItem")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Long.class)
public class CalendarItem {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    private Calendar calendar;
    private boolean isHoliday;
    private float value;
    private float previsionalValue;

	@JsonBackReference(value="resourceCalendar-calendarItem")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resourceCalendar_id", referencedColumnName = "id")
	private ResourceCalendar resourceCalendar; 
	
	@JsonBackReference(value="needProject-calendarItem")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "needProject_id", referencedColumnName = "id")
	private Need needProject; 
	
	@JsonBackReference(value="availability-calendarItem")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "availability_id", referencedColumnName = "id")
	private Availability availability; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public float getPrevisionalValue() {
		return previsionalValue;
	}
	public void setPrevisionalValue(float previsionalValue) {
		this.previsionalValue = previsionalValue;
	}
	
}