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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "resource")
public class Resource {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private long id; 

	private String firstname; 
	private String lastname;
	private String login;
	private String password;
	private String quadri;
	private int availabilityPerWeek;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ResourceCalendar.class, mappedBy = "resource")
    @JsonBackReference(value="resourceCalendar-resource")
	private Set<ResourceCalendar> resourceCalendars;

//	@JsonBackReference(value="task-resources")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "task_id", referencedColumnName = "id")
//    private Task task; 

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Task.class)
//    @JsonBackReference(value="project-resource")
	private Set<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Project.class)
//    @JsonBackReference(value="project-resource")
	private Set<Project> projects;
    
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Profile.class)
//    @JsonManagedReference(value="profile-resource")
	private Set<Profile> profiles;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Assignment.class, mappedBy = "resource")
    @JsonManagedReference(value="assignment-resource")
	private Set<Assignment> assignments;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	
}