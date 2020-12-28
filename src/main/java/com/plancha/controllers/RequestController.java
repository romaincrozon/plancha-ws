package com.plancha.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.Request;
import com.plancha.repositories.ProjectRepository;
import com.plancha.repositories.RequestRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping(value = "/request", produces = "application/json")
	public List<Request> getAllRequests() {
//		return requestRepository.findAllWithProjectName();
		List<Request> requests = requestRepository.findAll();
		List<Request> requestsWithProjectName = new ArrayList<Request>();
		for(Request request : requests) {
			long id = request.getProject().getId();
			request.setProject(projectRepository.findById(id).orElse(null));
//			request.getProject().setName(projectRepository.findNameById(request.getProject().getId()));
//			requestsWithProjectName.add(request);
		}
		return requests;
	}

	@GetMapping(value = "/request/{idRequest}", produces = "application/json")
	public Request getRequests(@PathVariable long idRequest) {
		return requestRepository.findById(idRequest).orElse(null);
	}
	
	@GetMapping(value = "/request/project/{idProject}", produces = "application/json")
	public List<Request> getRequestsByProject(@PathVariable long idProject) {
		return requestRepository.findByProjectId(idProject);
	}

	@PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
	public Request postRequestProfile(@RequestBody Request request) {
		return requestRepository.save(request);
	}

	@DeleteMapping(value = "/request", consumes = "application/json", produces = "application/json")
	public void deleteRequest(@RequestBody Request request) {
		requestRepository.delete(request);
	}	
}
