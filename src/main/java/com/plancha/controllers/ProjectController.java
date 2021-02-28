package com.plancha.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.CalendarList;
import com.plancha.dto.CalendarRange;
import com.plancha.dto.PlanchaCalendar;
import com.plancha.dto.entity.CalendarItem;
import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.SubProject;
import com.plancha.dto.entity.Task;
import com.plancha.repositories.ProjectRepository;
import com.plancha.repositories.SubProjectRepository;
import com.plancha.repositories.TaskRepository;
import com.plancha.utils.CalendarUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SubProjectRepository subProjectRepository;

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping(value = "/project", produces = "application/json")
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}
	
//	@PostMapping(value = "/projectsByDate", consumes = "application/json", produces = "application/json")
//	public List<Project> getProjectsByDate(@RequestBody CalendarRange calendarRange) {
//		if (calendarRange.getStartDate() != null && calendarRange.getEndDate() != null) {
//			return projectRepository.findProjectByDate(
//					CalendarUtils.stringToDate(calendarRange.getStartDate()), 
//					CalendarUtils.stringToDate(calendarRange.getEndDate()));
//		}
//		return null;
//	}

	@GetMapping(value = "/project/{idProject}", produces = "application/json")
	public Project getProject(@PathVariable Long idProject) {
		return projectRepository.findById(idProject).orElse(null);
	}

	@GetMapping(value = "/project/{idProject}/subProject", produces = "application/json")
	public List<SubProject> getProjectSubProjects(@PathVariable Long idProject) {
		//TODO
		return subProjectRepository.findAll();
	}
	
	@GetMapping(value = "/project/{idProject}/resources", produces = "application/json")
	public List<Project> getResourcesByProject(@PathVariable Long idProject) {
		return projectRepository.findAll();
	}

	@GetMapping(value = "/subProject", produces = "application/json")
	public List<SubProject> getSubProject() {
		return subProjectRepository.findAll();
	}
	
	@GetMapping(value = "/subProject/{idSubProject}", produces = "application/json")
	public SubProject getSubProject(@PathVariable Long idSubProject) {
		return subProjectRepository.findById(idSubProject).orElse(null);
	}

	@GetMapping(value = "/subProject/{idSubProject}/task", produces = "application/json")
	public List<Task> getSubProjectTasks(@PathVariable Long idSubProject) {
		//TODO
		return taskRepository.findAll();
	}

	@GetMapping(value = "/task", produces = "application/json")
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@GetMapping(value = "/task/{idTask}", produces = "application/json")
	public Task getTask(@PathVariable Long idTask) {
		return taskRepository.findById(idTask).orElse(null);
	}

	@PostMapping(value = "/project", consumes = "application/json", produces = "application/json")
	public Project postProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}

	@PostMapping(value = "/project/resource", consumes = "application/json", produces = "application/json")
	public Project addResourceToProject(@RequestBody Project project) {
//		project.getResourceList().stream().filter(resource -> )
		return projectRepository.save(project);
	}	

	@PostMapping(value = "/subProject", consumes = "application/json", produces = "application/json")
	public SubProject postSubProject(@RequestBody SubProject subProject) {
		return subProjectRepository.save(subProject);
	}

	@PostMapping(value = "/task", consumes = "application/json", produces = "application/json")
	public Task postTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	// Save or update
    @PutMapping("/project")
    Project saveOrUpdate(@RequestBody Project project) {
    	System.out.println("Update project");
    	Project projectInDatabase = projectRepository.findById(project.getId()).get();
    	if (projectInDatabase != null) {
    		if (project.getConfidencePercentage() > 0) {
    			projectInDatabase.setConfidencePercentage(project.getConfidencePercentage());
    		}
    		if (project.getName() != null) {
    			projectInDatabase.setName(project.getName());
    		}
    		if (project.getStatus() !=  null) {
    			projectInDatabase.setStatus(project.getStatus());
    		}
    		return projectRepository.save(projectInDatabase);
    	}
        return null;
    }
    
    //TODO : saveOrUpdate subproject

	@DeleteMapping(value = "/project/{idProject}")
	public void deleteProject(@PathVariable Long idProject) {
		projectRepository.deleteById(idProject);
	}	
	@DeleteMapping(value = "/subProject/{idSubProject}", consumes = "application/json", produces = "application/json")
	public void deleteSubProject(@PathVariable Long idSubProject) {
		subProjectRepository.deleteById(idSubProject);
	}	
	@DeleteMapping(value = "/task/{idTask}", consumes = "application/json", produces = "application/json")
	public void deleteTask(@PathVariable Long idTask) {
		taskRepository.deleteById(idTask);
	}	
}
