package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.CalendarRange;
import com.plancha.dto.ProjectResource;
import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.ResourceCalendar;
import com.plancha.repositories.ResourceCalendarRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ResourceCalendarController {

	@Autowired
	private ResourceCalendarRepository resourceCalendarRepository;

	@GetMapping(value = "/resourceCalendar", produces = "application/json")
	public List<ResourceCalendar> getAllResourceCalendar() {
		return resourceCalendarRepository.findAll();
	}

	@PostMapping(value = "/resourceCalendarsByDate/project/{projectId}", produces = "application/json")
	public List<ResourceCalendar> getResourceCalendarsByDateAndProject(@PathVariable Long projectId, @RequestBody CalendarRange calendarRange) {
		return resourceCalendarRepository.findResourceCalendarByDateAndProject(calendarRange.getStartDate(), calendarRange.getEndDate(), projectId);
	}

	@PostMapping(value = "/resourceCalendar", consumes = "application/json", produces = "application/json")
	public ResourceCalendar postResourceCalendar(@RequestBody ResourceCalendar resourceCalendar) {
		ResourceCalendar resourceCalendarInDatabase = resourceCalendarRepository.findByProjectIdAndResourceId(resourceCalendar.getProject().getId(), resourceCalendar.getResource().getId()).orElse(null); 
		if (resourceCalendarInDatabase == null) {
			return resourceCalendarRepository.save(resourceCalendar);
		}
		return resourceCalendarInDatabase; 
	}
	
	@PostMapping(value = "/resourceCalendarByProjectAndResource", consumes = "application/json", produces = "application/json")
	public ResourceCalendar postResourceCalendarFromProjectAndResource(@RequestBody ProjectResource projectResource) {
		ResourceCalendar resourceCalendarInDatabase = resourceCalendarRepository.findByProjectIdAndResourceId(projectResource.getProjectId(), projectResource.getResourceId()).orElse(null); 
		if (resourceCalendarInDatabase == null) {
			ResourceCalendar resourceCalendar = new ResourceCalendar(projectResource.getProjectId(), projectResource.getResourceId());
			return resourceCalendarRepository.save(resourceCalendar);
		}
		return resourceCalendarInDatabase; 
	}

	// Save or update
    @PutMapping("/resourceCalendar")
    Project saveOrUpdateResourceCalendar(@RequestBody ResourceCalendar resourceCalendar) {
    	System.out.println("Update resource calendar");
    	ResourceCalendar resourceCalendarInDatabase = resourceCalendarRepository.findById(resourceCalendar.getId()).get();
    	if (resourceCalendarInDatabase != null) {
    		//TODO
    	}
        return null;
    }
    
	@DeleteMapping(value = "/resourceCalendar", consumes = "application/json", produces = "application/json")
	public void deleteResourceCalendar(@RequestBody ResourceCalendar resourceCalendar) {
		resourceCalendarRepository.delete(resourceCalendar);
	}	
}
