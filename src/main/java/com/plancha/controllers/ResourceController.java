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

import com.plancha.dto.entity.Resource;
import com.plancha.repositories.ResourceRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ResourceController {

	@Autowired
	private ResourceRepository resourceRepository;

	@GetMapping(value = "/resource", produces = "application/json")
	public List<Resource> getAllResources() {
		return resourceRepository.findAll();
	}
	
	@GetMapping(value = "/resource/{idResource}", produces = "application/json")
	public Resource getResource(@PathVariable long idResource) {
		return resourceRepository.findById(idResource).orElse(null);
	}	

	@PostMapping(value = "/resource", consumes = "application/json", produces = "application/json")
	public Resource postResource(@RequestBody Resource resource) {
		return resourceRepository.save(resource);
	}	
	
	@DeleteMapping(value = "/resource", consumes = "application/json", produces = "application/json")
	public void deleteResource(@RequestBody Resource resource) {
		resourceRepository.delete(resource);
	}	
}
