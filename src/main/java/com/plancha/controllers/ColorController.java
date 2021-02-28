package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.entity.Color;
import com.plancha.repositories.ColorRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ColorController {

	@Autowired
	private ColorRepository colorRepository;
	
	@GetMapping(value = "/color", produces = "application/json")
	public List<Color> getAllColors() {
		return colorRepository.findAll();
	}
	
	@PostMapping(value = "/color", consumes = "application/json", produces = "application/json")
	public Color postAssignment(@RequestBody Color color) {
		return colorRepository.save(color);
	}	
	
	@DeleteMapping(value = "/color", consumes = "application/json", produces = "application/json")
	public void deleteColor(@RequestBody Color color) {
		colorRepository.delete(color);
	}	
		
}
