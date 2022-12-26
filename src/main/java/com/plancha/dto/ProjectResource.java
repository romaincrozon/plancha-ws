package com.plancha.dto;

public class ProjectResource {

	Long resourceId;
	Long projectId;
	
	public ProjectResource(Long resourceId, Long projectId) {
		this.resourceId = resourceId;
		this.projectId = projectId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
