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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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

	private String status;
	private int confidencePercentage;

	private float soldWorkload;
	private float challengedWorkload;
	private float consumedWorkload;
	private float projectMargin;
	private String color;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = SubProject.class, mappedBy = "project")
    @JsonManagedReference(value="subProject-project")
	@OrderBy("name ASC")
	private Set<SubProject> subProjectList;

//    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class, mappedBy = "projects")
//    @JsonManagedReference(value="resource-project")
//    @OrderBy("quadri ASC")
//    @JoinTable(name = "profile_competence", 
//	  	joinColumns = @JoinColumn(name = "competence_id"), 
//	  	inverseJoinColumns = @JoinColumn(name = "profile_id"))
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class, fetch = FetchType.EAGER)
    @JoinTable(name = "project_resource", 
	  	joinColumns = @JoinColumn(name = "resource_id"), 
	  	inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Resource> resourceList;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class, mappedBy = "project")
    @JsonSerialize(using = RequestSerializer.class)
	private Set<Request> requestList;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<SubProject> getSubProjectList() {
		return subProjectList;
	}
	public void setSubProjectList(Set<SubProject> subProjectList) {
		this.subProjectList = subProjectList;
	}
	public int getConfidencePercentage() {
		return confidencePercentage;
	}
	public void setConfidencePercentage(int confidencePercentage) {
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
	public Set<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(Set<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public Set<Request> getRequestList() {
		return requestList;
	}
	public void setRequestList(Set<Request> requestList) {
		this.requestList = requestList;
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
	public float getProjectMargin() {
		return projectMargin;
	}
	public void setProjectMargin(float projectMargin) {
		this.projectMargin = projectMargin;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}