package com.plancha.dto.entity;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "resourceCalendar")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Long.class)
public class ResourceCalendar {
	
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference(value="resourceCalendar-resource")
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Resource resource;
    
    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = CalendarItem.class, mappedBy = "resourceCalendar")
    @JsonManagedReference(value="resourceCalendar-calendarItem")
    @OrderBy(value = "calendar ASC")
	private Set<CalendarItem> calendarItems;

//	@JsonBackReference(value="task-resourceCalendar")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
	@JsonIdentityReference(alwaysAsId = true)
    private Task task; 

	public ResourceCalendar(Long taskId, Long resourceId) {
		this.task = new Task(taskId);
		this.resource = new Resource(resourceId);
	}
	
	public ResourceCalendar() {
	}

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
	public Set<CalendarItem> getCalendarItems() {
		return calendarItems;
	}
	public void setCalendarItems(Set<CalendarItem> calendarItems) {
		this.calendarItems = calendarItems;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}