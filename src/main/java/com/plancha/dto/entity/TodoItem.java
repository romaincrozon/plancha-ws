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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "todo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "id", 
scope     = Long.class)
public class TodoItem extends com.plancha.dto.entity.Entity{
	
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 	
    @Column(columnDefinition = "serial")
    private Long id; 
    
    private String detail;
    private Calendar creationDate;
    private Calendar deadline;
    private int recurrence;//every day, every week

    private int status;
    private int todoItemCategory ;//personal, global, teamleader, techlead

//    @JsonBackReference(value="todo-createdBy")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private Resource createdBy;
    
//    @JsonBackReference(value="todo-affectedTo")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "affected_to_id", referencedColumnName = "id")
    private Resource affectedTo;

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getDeadline() {
		return deadline;
	}

	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}

	public int getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(int recurrence) {
		this.recurrence = recurrence;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTodoItemCategory() {
		return todoItemCategory;
	}

	public void setTodoItemCategory(int todoItemCategory) {
		this.todoItemCategory = todoItemCategory;
	}

	public Resource getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Resource createdBy) {
		this.createdBy = createdBy;
	}

	public Resource getAffectedTo() {
		return affectedTo;
	}

	public void setAffectedTo(Resource affectedTo) {
		this.affectedTo = affectedTo;
	}
}