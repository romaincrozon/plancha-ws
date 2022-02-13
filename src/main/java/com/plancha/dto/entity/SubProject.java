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
@Table(name = "subProject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class SubProject extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    @JsonBackReference(value="subProject-project")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Project project; 

	private String status;

	private float soldWorkload;
	private float challengedWorkload;
	private float consumedWorkload;
	private String color;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Task.class, mappedBy = "subProject")
    @JsonManagedReference(value="task-subProject")
	@OrderBy("name ASC")
	private Set<Task> taskList;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	} 

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Set<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(Set<Task> taskList) {
		this.taskList = taskList;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}