package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping(value = "/resourceCalendar", consumes = "application/json", produces = "application/json")
	public ResourceCalendar postResourceCalendar(@RequestBody ResourceCalendar resourceCalendar) {
		return resourceCalendarRepository.save(resourceCalendar);
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
