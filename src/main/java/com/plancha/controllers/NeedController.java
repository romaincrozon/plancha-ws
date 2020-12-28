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

import com.plancha.dto.entity.Need;
import com.plancha.repositories.NeedRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NeedController {

	@Autowired
	private NeedRepository needRepository;
	
	@GetMapping(value = "/need", produces = "application/json")
	public List<Need> getAllNeeds() {
		return needRepository.findAll();
	}
	
	@GetMapping(value = "/need/{idResource}", produces = "application/json")
	public Need getNeed(@PathVariable long idNeed) {
		return needRepository.findById(idNeed).orElse(null);
	}	

	@PostMapping(value = "/need", consumes = "application/json", produces = "application/json")
	public Need postNeed(@RequestBody Need need) {
		return needRepository.save(need);
	}	
	
	@DeleteMapping(value = "/need", consumes = "application/json", produces = "application/json")
	public void deleteNeed(@RequestBody Need need) {
		needRepository.delete(need);
	}	
}
