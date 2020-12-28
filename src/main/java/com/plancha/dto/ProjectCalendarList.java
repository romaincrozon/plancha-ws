package com.plancha.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.plancha.dto.entity.Project;

public class ProjectCalendarList {
	
	private List<Project> projectList;
	
	public ProjectCalendarList() {
		
	}
}