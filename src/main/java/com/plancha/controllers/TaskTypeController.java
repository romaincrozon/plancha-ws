package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.entity.TaskType;
import com.plancha.repositories.TaskTypeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskTypeController {

	@Autowired
	private TaskTypeRepository taskTypeRepository;
	
	@GetMapping(value = "/taskType", produces = "application/json")
	public List<TaskType> getAllTaskTypes() {
		return taskTypeRepository.findAll();
	}
	
	@PostMapping(value = "/taskType", consumes = "application/json", produces = "application/json")
	public TaskType postTaskType(@RequestBody TaskType taskType) {
		return taskTypeRepository.save(taskType);
	}	
	
	@DeleteMapping(value = "/taskType")
	public void deleteColor(@RequestBody TaskType taskType) {
		taskTypeRepository.delete(taskType);
	}	
}