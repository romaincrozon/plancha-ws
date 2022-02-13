package com.plancha.dto.entity;

import java.util.Calendar;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.plancha.serializer.OnlyNameSerializer;
import com.plancha.serializer.ResourceSerializer;

@Entity
@Table(name = "need")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Need {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonSerialize(using = OnlyNameSerializer.class)
    private Profile profile;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonSerialize(using = OnlyNameSerializer.class)
    private Project project;

    private Calendar creationDate;
    private int criticity;
    private int type;
    private int status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestor_id", referencedColumnName = "id")
    @JsonSerialize(using = ResourceSerializer.class)
    private Resource requestor;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = CalendarValue.class, mappedBy = "need")
    @JsonManagedReference(value="need-calendarValue")
    @OrderBy(value = "calendar ASC")
    private List<CalendarValue> calendarValues;

    private boolean isProjectNeed;
    private String comment;
    
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public int getCriticity() {
		return criticity;
	}
	public void setCriticity(int criticity) {
		this.criticity = criticity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Resource getRequestor() {
		return requestor;
	}
	public void setRequestor(Resource requestor) {
		this.requestor = requestor;
	}
	public List<CalendarValue> getCalendarValues() {
		return calendarValues;
	}
	public void setCalendarValues(List<CalendarValue> calendarValues) {
		this.calendarValues = calendarValues;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isProjectNeed() {
		return isProjectNeed;
	}
	public void setProjectNeed(boolean isProjectNeed) {
		this.isProjectNeed = isProjectNeed;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
}