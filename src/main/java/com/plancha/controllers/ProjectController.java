package com.plancha.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.plancha.dto.entity.Project;
import com.plancha.repositories.ProjectRepository;
import com.plancha.repositories.ResourceRepository;
import com.plancha.utils.Utils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;

	@GetMapping(value = "/project", produces = "application/json")
	public List<Project> getProjects() {
		return projectRepository.findByParent(null);
	}

	@GetMapping(value = "/project/all", produces = "application/json")
	public List<Project> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(project -> project.cleanParentProject()).collect(Collectors.toList());
	}

	@GetMapping(value = "/project/{idProject}", produces = "application/json")
	public Project getProject(@PathVariable Long idProject) {
		return projectRepository.findById(idProject).orElse(null);
	}

	@GetMapping(value = "/project/{idProject}/resources", produces = "application/json")
	public List<Project> getResourcesByProject(@PathVariable Long idProject) {
		return projectRepository.findAll();
	}

	@PostMapping(value = "/project", consumes = "application/json", produces = "application/json")
	public Project postProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}

//	@PostMapping(value = "/project/{projectId}/resource", consumes = "application/json", produces = "application/json")
//	public Project addResourceToProject(@PathVariable long projectId, @RequestBody Resource resource) {
//		Project project = projectRepository.findById(projectId).orElse(null);
//		Resource resourceObj = resourceRepository.findById(resource.getId()).orElse(null);
//		if (project != null && resourceObj != null) {
//			project.getResources().add(resourceObj);
//		}
//		return projectRepository.save(project);
//	}	

    @PutMapping("/project")
    Project saveOrUpdate(@RequestBody Project project) {
    	System.out.println("Update project");
    	return projectRepository.save(project);
    }

    @DeleteMapping(value = "/project/{idProject}")
	public void deleteProject(@PathVariable Long idProject) {
		projectRepository.deleteById(idProject);
	}	
}
