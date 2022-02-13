package com.plancha.dto.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "availability")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Availability {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 
	private int implication;
    private Calendar creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
	private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestor_id", referencedColumnName = "id")
    private Resource requestor;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = CalendarItem.class, mappedBy = "availability")
    @JsonManagedReference(value="availability-calendarItem")
    @OrderBy(value = "calendar ASC")
    private List<CalendarItem> calendarItems;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Resource getRequestor() {
		return requestor;
	}
	public void setRequestor(Resource requestor) {
		this.requestor = requestor;
	}
	public List<CalendarItem> getCalendarItems() {
		return calendarItems;
	}
	public void setCalendarItems(List<CalendarItem> calendarItems) {
		this.calendarItems = calendarItems;
	}
	public int getImplication() {
		return implication;
	}
	public void setImplication(int implication) {
		this.implication = implication;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
}