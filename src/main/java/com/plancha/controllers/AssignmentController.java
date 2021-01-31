package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.entity.Assignment;
import com.plancha.repositories.AssignmentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@GetMapping(value = "/assignment", produces = "application/json")
	public List<Assignment > getAllAssignments() {
		return assignmentRepository.findAll();
	}
	
	@GetMapping(value = "/assignment/{idResource}", produces = "application/json")
	public Assignment getAssignment(@PathVariable Long idAssignment) {
		return assignmentRepository.findById(idAssignment).orElse(null);
	}	

	@PostMapping(value = "/assignment", consumes = "application/json", produces = "application/json")
	public Assignment postAssignment(@RequestBody Assignment assignment) {
		return assignmentRepository.save(assignment);
	}	
	
	@DeleteMapping(value = "/assignment", consumes = "application/json", produces = "application/json")
	public void deleteAssignment(@RequestBody Assignment assignment) {
		assignmentRepository.delete(assignment);
	}	
		
}
