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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "task")
public class Task {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private long id; 

	private String name; 
	private String status;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ResourceCalendar.class, mappedBy = "task")
	@JsonManagedReference(value="task-resourceCalendar")
    @OrderBy(value = "resource ASC")
	private Set<ResourceCalendar> resourceCalendars;
	
	@JsonBackReference(value="task-subProject")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subProject_id", referencedColumnName = "id")
    private SubProject subProject; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SubProject getSubProject() {
		return subProject;
	}
	public void setSubProject(SubProject subProject) {
		this.subProject = subProject;
	}
	public Set<ResourceCalendar> getResourceCalendars() {
		return resourceCalendars;
	}
	public void setResourceCalendars(Set<ResourceCalendar> resourceCalendars) {
		this.resourceCalendars = resourceCalendars;
	}
}