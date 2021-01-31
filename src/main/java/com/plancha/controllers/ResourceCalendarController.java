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
import com.plancha.dto.TaskResource;
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

	@PostMapping(value = "/resourceCalendarsByDate/task/{taskId}", produces = "application/json")
	public List<ResourceCalendar> getResourceCalendarsByDateAndTask(@PathVariable Long taskId, @RequestBody CalendarRange calendarRange) {
		return resourceCalendarRepository.findResourceCalendarByDateAndTask(calendarRange.getStartDate(), calendarRange.getEndDate(), taskId);
	}

	@PostMapping(value = "/resourceCalendar", consumes = "application/json", produces = "application/json")
	public ResourceCalendar postResourceCalendar(@RequestBody ResourceCalendar resourceCalendar) {
		ResourceCalendar resourceCalendarInDatabase = resourceCalendarRepository.findByTaskIdAndResourceId(resourceCalendar.getTask().getId(), resourceCalendar.getResource().getId()).orElse(null); 
		if (resourceCalendarInDatabase == null) {
			return resourceCalendarRepository.save(resourceCalendar);
		}
		return resourceCalendarInDatabase; 
	}
	
	@PostMapping(value = "/resourceCalendarByTaskAndResource", consumes = "application/json", produces = "application/json")
	public ResourceCalendar postResourceCalendarFromTaskAndResource(@RequestBody TaskResource taskResource) {
		ResourceCalendar resourceCalendarInDatabase = resourceCalendarRepository.findByTaskIdAndResourceId(taskResource.getTaskId(), taskResource.getResourceId()).orElse(null); 
		if (resourceCalendarInDatabase == null) {
			ResourceCalendar resourceCalendar = new ResourceCalendar(taskResource.getTaskId(), taskResource.getResourceId());
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
