package com.plancha.dto.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "color")
public class Color {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    private String code;

//	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = TaskType.class, mappedBy = "color")
//    @JsonBackReference(value="taskType-color")
//	private Set<Task> tasks;
//	
//	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = SubProject.class, mappedBy = "color")
//    @JsonBackReference(value="subproject-color")
//	private Set<SubProject> subProjects;
//
//	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Project.class, mappedBy = "color")
//    @JsonBackReference(value="project-color")
//	private Set<Project> projects;
	
//	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Resource.class, mappedBy = "color")
//    @JsonBackReference(value="resource-color")
//	private Set<Resource> resources;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public Set<Task> getTasks() {
//		return tasks;
//	}
//
//	public void setTasks(Set<Task> tasks) {
//		this.tasks = tasks;
//	}
//
//	public Set<SubProject> getSubProjects() {
//		return subProjects;
//	}
//
//	public void setSubProjects(Set<SubProject> subProjects) {
//		this.subProjects = subProjects;
//	}
//
//	public Set<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(Set<Project> projects) {
//		this.projects = projects;
//	}
}
