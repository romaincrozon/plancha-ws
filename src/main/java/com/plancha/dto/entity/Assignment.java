package com.plancha.dto.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "assignment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Assignment {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 
    
    private int daysPerWeek;
    private Calendar beginDate;
    private Calendar endDate;

//    @JsonBackReference(value="assignment-resource")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
//    @JsonBackReference(value="project-assignment")
//	private Project project;

    @JsonBackReference(value="assignment-resource")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resource resource;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Project getProject() {
//		return project;
//	}
//	public void setProject(Project project) {
//		this.project = project;
//	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public int getDaysPerWeek() {
		return daysPerWeek;
	}
	public void setDaysPerWeek(int daysPerWeek) {
		this.daysPerWeek = daysPerWeek;
	}
	public Calendar getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
//	public Project getProject() {
//		return project;
//	}
//	public void setProject(Project project) {
//		this.project = project;
//	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
}