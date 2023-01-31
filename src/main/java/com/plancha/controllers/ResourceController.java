package com.plancha.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResourceController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody Resource resource) {
    	if (resourceRepository.findByUsername(resource.getUsername()) == null) {
    		resource.setPassword(bCryptPasswordEncoder.encode(resource.getPassword()));
    		resourceRepository.save(resource);
    	}
    }

	@GetMapping(value = "/resource", produces = "application/json")
	public List<Resource> getResources() {
		List<Resource> resources = resourceRepository.findAll();
		return resources.stream().map(resource -> resource.cleanResourceCalendars()).collect(Collectors.toList());
	}

	@GetMapping(value = "/resource/{idResource}", produces = "application/json")
	public Resource getResource(@PathVariable Long idResource) {
		return resourceRepository.findById(idResource).orElse(null);
	}

	@PostMapping(value = "/resource", consumes = "application/json", produces = "application/json")
	public Resource postResource(@RequestBody Resource resource) {
		return resourceRepository.save(resource);
	}

	@DeleteMapping(value = "/resource/{idResource}")
	public void deleteResource(@PathVariable Long idResource) {
		resourceRepository.deleteById(idResource);
	}
}
