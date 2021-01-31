package com.plancha.dto.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "profile")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Profile {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

	private String name; 

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Profile.class)
//    @JoinTable(name = "profile_competence", 
//    	joinColumns = @JoinColumn(name = "profile_id"), 
//    	inverseJoinColumns = @JoinColumn(name = "competence_id"))
    private Set<Competence> competenceList;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class)
    @JsonManagedReference(value="profile-request")
    private Set<Request> requestList;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class)
    private Set<Resource> resourceList;

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

	public Set<Competence> getCompetenceList() {
		return competenceList;
	}

	public void setCompetenceList(Set<Competence> competenceList) {
		this.competenceList = competenceList;
	}

	public Set<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(Set<Request> requestList) {
		this.requestList = requestList;
	}

	public Set<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(Set<Resource> resourceList) {
		this.resourceList = resourceList;
	}
    

    
}