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
@Table(name = "calendarValue")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Long.class)
public class CalendarValue {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    private Calendar calendar;
    private float value;
	
	@JsonBackReference(value="need-calendarValue")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "need_id", referencedColumnName = "id")
	private Need need; 
	
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
}