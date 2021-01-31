package com.plancha.dto;

public class TaskResource {

	Long resourceId;
	Long taskId;
	
	public TaskResource(Long resourceId, Long taskId) {
		this.resourceId = resourceId;
		this.taskId = taskId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
