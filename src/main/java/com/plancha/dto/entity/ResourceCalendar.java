package com.plancha.dto.entity;

import java.util.Calendar;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "resourceCalendar")
public class ResourceCalendar {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference(value="resourceCalendar-resource")
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
	private Resource resource;
    
    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = CalendarItem.class, mappedBy = "resourceCalendar")
    @JsonManagedReference(value="resourceCalendar-calendarItem")
    @OrderBy(value = "calendar ASC")
	private Set<CalendarItem> calendarItems;

	@JsonBackReference(value="task-resourceCalendar")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task; 


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Set<CalendarItem> getCalendarItems() {
		return calendarItems;
	}
	public void setCalendarItems(Set<CalendarItem> calendarItems) {
		this.calendarItems = calendarItems;
	}
}