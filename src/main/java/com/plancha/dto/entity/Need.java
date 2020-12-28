package com.plancha.dto.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "need")
public class Need {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private long id; 

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Resource.class)
    @JsonBackReference
	private Set<Resource> resource;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Resource> getResource() {
		return resource;
	}
	public void setResource(Set<Resource> resource) {
		this.resource = resource;
	}
}