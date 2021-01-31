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

import com.plancha.dto.entity.Competence;
import com.plancha.repositories.CompetenceRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CompetenceController {

	@Autowired
	private CompetenceRepository competenceRepository;

	@GetMapping(value = "/competence", produces = "application/json")
	public List<Competence> getAllCompetences() {
		return competenceRepository.findAll();
	}

	@GetMapping(value = "/competence/{idCompetence}", produces = "application/json")
	public Competence getCompetence(@PathVariable Long idCompetence) {
		return competenceRepository.findById(idCompetence).orElse(null);
	}

	@PostMapping(value = "/competence", consumes = "application/json", produces = "application/json")
	public Competence postCompetence(@RequestBody Competence competence) {
		return competenceRepository.save(competence);
	}

	@DeleteMapping(value = "/competence", consumes = "application/json", produces = "application/json")
	public void deleteCompetence(@RequestBody Competence competence) {
		competenceRepository.delete(competence);
	}	
}
