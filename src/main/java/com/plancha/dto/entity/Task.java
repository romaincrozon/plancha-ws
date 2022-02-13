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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "task")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Task extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

	private String status;
	
//	@JsonManagedReference(value="task-taskType")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeTask_id", referencedColumnName = "id")
	private TaskType taskType;

	private float soldWorkload;
	private float challengedWorkload;
	private float consumedWorkload;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ResourceCalendar.class, mappedBy = "task")
//	@JsonManagedReference(value="task-resourceCalendar")
    @OrderBy(value = "resource ASC")
	private Set<ResourceCalendar> resourceCalendars;
	
	@JsonBackReference(value="task-subProject")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subProject_id", referencedColumnName = "id")
    private SubProject subProject; 

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	} 

	public Task(Long id) {
		this.id = id;
	}
	public Task() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public TaskType getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	public float getSoldWorkload() {
		return soldWorkload;
	}
	public void setSoldWorkload(float soldWorkload) {
		this.soldWorkload = soldWorkload;
	}
	public float getChallengedWorkload() {
		return challengedWorkload;
	}
	public void setChallengedWorkload(float challengedWorkload) {
		this.challengedWorkload = challengedWorkload;
	}
	public float getConsumedWorkload() {
		return consumedWorkload;
	}
	public void setConsumedWorkload(float consumedWorkload) {
		this.consumedWorkload = consumedWorkload;
	}
}