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
	scope     = Long.class)
public class Project {
	
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

	private String name; 
	private String status;
	private int confidencePercentage;

	private float soldWorkload;
	private float challengedWorkload;
	private float consumedWorkload;
	private float projectMargin;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = SubProject.class, mappedBy = "project")
    @JsonManagedReference(value="subProject-project")
	@OrderBy("name ASC")
	private Set<SubProject> subProjectList;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class)
//    @JsonManagedReference(value="resource-project")
    private Set<Resource> resourceList;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class, mappedBy = "project")
//    @JsonManagedReference(value="request-project")
    @JsonSerialize(using = RequestSerializer.class)
	private Set<Request> requestList;

//	@JsonManagedReference(value="project-color")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}