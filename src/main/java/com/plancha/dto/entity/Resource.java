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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "resource")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property  = "id", 
	scope     = Long.class)
public class Resource {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

	private String firstname; 
	private String lastname;
	private String username;
	private String password;
	private String quadri;
	private int availabilityPerWeek;
	private String token;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ResourceCalendar.class, mappedBy = "resource")
//    @JsonManagedReference(value="resourceCalendar-resource")
	private Set<ResourceCalendar> resourceCalendars;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Task.class)
//    @JsonBackReference(value="project-resource")
	private Set<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Project.class, fetch = FetchType.EAGER)
//    @JsonBackReference(value="resource-project")
	@JsonIdentityReference(alwaysAsId = true)
	private Set<Project> projects;
    
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Profile.class, fetch = FetchType.EAGER)
//    @JsonManagedReference(value="profile-resource")
	private Set<Profile> profiles;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Assignment.class, mappedBy = "resource", fetch = FetchType.EAGER)
    @JsonManagedReference(value="assignment-resource")
	private Set<Assignment> assignments;
    
    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = TodoItem.class, mappedBy = "resource", fetch = FetchType.EAGER)
    @JsonManagedReference(value="todo-resource")
	private Set<TodoItem> todoItems;
    
	public Resource(Long id) {
		this.id = id;
	}
	public Resource() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuadri() {
		return quadri;
	}
	public void setQuadri(String quadri) {
		this.quadri = quadri;
	}
	public int getAvailabilityPerWeek() {
		return availabilityPerWeek;
	}
	public void setAvailabilityPerWeek(int availabilityPerWeek) {
		this.availabilityPerWeek = availabilityPerWeek;
	}
	public Set<ResourceCalendar> getResourceCalendars() {
		return resourceCalendars;
	}
	public void setResourceCalendars(Set<ResourceCalendar> resourceCalendars) {
		this.resourceCalendars = resourceCalendars;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	public Set<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}
	public Set<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}