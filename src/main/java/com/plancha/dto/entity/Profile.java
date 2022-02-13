package com.plancha.dto.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.plancha.serializer.CompetenceSerializer;

@Entity
@Table(name = "profile")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class Profile extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    @ManyToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    @JsonSerialize(using = CompetenceSerializer.class)
    private List<Competence> competences;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Request.class)
    @JsonManagedReference(value="profile-request")
    private Set<Request> requestList;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Resource.class)
    private Set<Resource> resourceList;

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

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
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