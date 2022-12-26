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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.plancha.serializer.RequestSerializer;

@Entity
@Table(name = "project")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Project.class)
public class Project extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private Long id; 
    
//    @JsonBackReference(value="project-project")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id", referencedColumnName = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition="integer", name = "parent_id", nullable = true)
    private Project parent; 

	private String status;
	private Integer confidencePercentage;

	private Float soldWorkload;
	
	@Column(name="challenged_workload", nullable = true)
	private Float challengedWorkload;
	
	private Float consumedWorkload;
	private Float projectMargin;
	private String color;

//	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Project.class, mappedBy = "parent")
//    @JsonManagedReference(value="project-project")
//	@OrderBy("name ASC")
	@OneToMany(mappedBy="parent",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Project> projects;

//    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class, mappedBy = "projects")
//    @JsonManagedReference(value="resource-project")
//    @OrderBy("quadri ASC")
//    @JoinTable(name = "profile_competence", 
//	  	joinColumns = @JoinColumn(name = "competence_id"), 
//	  	inverseJoinColumns = @JoinColumn(name = "profile_id"))

//	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "project_resource", 
//	  	joinColumns = @JoinColumn(name = "resource_id"), 
//	  	inverseJoinColumns = @JoinColumn(name = "project_id"))
//	private Set<Resource> resources;

//    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class, mappedBy = "project")
//    @JsonSerialize(using = RequestSerializer.class)
//	private Set<Request> requestList;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ResourceCalendar.class, mappedBy = "project")
    @OrderBy(value = "resource ASC")
	private Set<ResourceCalendar> resourceCalendars;

	private String name;
	
//	public Project(Long id) {
//		this.id = id;
//	}

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	public Integer getConfidencePercentage() {
		return confidencePercentage;
	}
	public void setConfidencePercentage(Integer confidencePercentage) {
		this.confidencePercentage = confidencePercentage;
	}
//	public Set<Resource> getResourceList() {
//		return resourceList;
//	}
//	public void setResourceList(Set<Resource> resourceList) {
//		this.resourceList = resourceList;
//	}
//	public Set<Request> getRequestList() {
//		return requestList;
//	}
//	public void setRequestList(Set<Request> requestList) {
//		this.requestList = requestList;
//	}
//	public Set<Resource> getResources() {
//		return resources;
//	}
//	public void setResources(Set<Resource> resources) {
//		this.resources = resources;
//	}
//	public Set<Request> getRequestList() {
//		return requestList;
//	}
//	public void setRequestList(Set<Request> requestList) {
//		this.requestList = requestList;
//	}
	public Float getSoldWorkload() {
		return soldWorkload;
	}
	public void setSoldWorkload(Float soldWorkload) {
		this.soldWorkload = soldWorkload;
	}
	public Float getChallengedWorkload() {
		return challengedWorkload;
	}
	public void setChallengedWorkload(Float challengedWorkload) {
		this.challengedWorkload = challengedWorkload;
	}
	public Float getConsumedWorkload() {
		return consumedWorkload;
	}
	public void setConsumedWorkload(Float consumedWorkload) {
		this.consumedWorkload = consumedWorkload;
	}
	public Float getProjectMargin() {
		return projectMargin;
	}
	public void setProjectMargin(Float projectMargin) {
		this.projectMargin = projectMargin;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public Project getParent() {
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	public Set<ResourceCalendar> getResourceCalendars() {
		return resourceCalendars;
	}

	public void setResourceCalendars(Set<ResourceCalendar> resourceCalendars) {
		this.resourceCalendars = resourceCalendars;
	}
}