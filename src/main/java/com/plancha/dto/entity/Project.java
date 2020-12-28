package com.plancha.dto.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.plancha.serializer.RequestSerializer;

@Entity
@Table(name = "project")
//@JsonIdentityInfo(
//  generator = ObjectIdGenerators.PropertyGenerator.class, 
//  property = "name")
public class Project {
	
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 	
    @Column(columnDefinition = "serial")
    private long id; 

	private String name; 
	private String status;
	private int confidencePercentage;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = SubProject.class, mappedBy = "project")
    @JsonManagedReference(value="subProject-project")
	@OrderBy("name ASC")
	private Set<SubProject> subProjectList;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class)
    private Set<Resource> resourceList;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class, mappedBy = "project")
//    @JsonManagedReference(value="request-project")
    @JsonSerialize(using = RequestSerializer.class)
	private Set<Request> requestList;
    
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
}