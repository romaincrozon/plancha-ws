package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.entity.Availability;
import com.plancha.repositories.AvailabilityRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AvailabilityController {

	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	@GetMapping(value = "/availability", produces = "application/json")
	public List<Availability> getAllAvailabilities() {
		return availabilityRepository.findAll();
	}

	@PostMapping(value = "/availability", consumes = "application/json", produces = "application/json")
	public Availability postAvailability(@RequestBody Availability availability) {
		return availabilityRepository.save(availability);
	}	
	
	@DeleteMapping(value = "/availability", consumes = "application/json", produces = "application/json")
	public void deleteAvailability(@RequestBody Availability availability) {
		availabilityRepository.delete(availability);
	}	
}
