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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "taskType")
public class TaskType {
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 

    private String type;

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = Task.class, mappedBy = "taskType")
//    @JsonBackReference(value="task-taskType")
	@OrderBy("name ASC")
	private Set<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
