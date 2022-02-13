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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "competence")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Long.class)
public class Competence extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Profile.class, fetch = FetchType.EAGER)
    @JoinTable(name = "profile_competence", 
	  	joinColumns = @JoinColumn(name = "competence_id"), 
	  	inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private Set<Profile> profile;

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

	public Set<Profile> getProfile() {
		return profile;
	}

	public void setProfile(Set<Profile> profile) {
		this.profile = profile;
	}
}