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
import com.plancha.dto.entity.Profile;
import com.plancha.repositories.ProfileRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;

	@GetMapping(value = "/profile", produces = "application/json")
	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}

	@GetMapping(value = "/profile/{idProfile}", produces = "application/json")
	public Profile getProfile(@PathVariable long idProfile) {
		return profileRepository.findById(idProfile).orElse(null);
	}

	@PostMapping(value = "/profile", consumes = "application/json", produces = "application/json")
	public Profile postProfile(@RequestBody Profile profile) {
		return profileRepository.save(profile);
	}

	@DeleteMapping(value = "/profile", consumes = "application/json", produces = "application/json")
	public void deleteProfile(@RequestBody Profile profile) {
		profileRepository.delete(profile);
	}	
}
