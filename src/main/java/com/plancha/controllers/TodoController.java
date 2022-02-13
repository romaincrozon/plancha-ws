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

import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.TodoItem;
import com.plancha.repositories.TodoItemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@GetMapping(value = "/todo", produces = "application/json")
	public List<TodoItem> getAllTodo() {
		return todoItemRepository.findAllByOrderByStatusAsc();
	}
	
	@GetMapping(value = "/todo/{id}", produces = "application/json")
	public TodoItem getTodo(@PathVariable Long id) {
		return todoItemRepository.findById(id).orElse(null);
	}
	
	// Save or update
    @PutMapping(value = "/todo/{id}", consumes = "application/json", produces = "application/json")
    TodoItem saveOrUpdate(@RequestBody TodoItem todoItem) {
    	System.out.println("Update todo item");
//    	TodoItem todoItemInDatabase = todoItemRepository.findById(todoItem.getId()).get();
//    	if (todoItemInDatabase != null) {
//    		if (project.getConfidencePercentage() > 0) {
//    			projectInDatabase.setConfidencePercentage(project.getConfidencePercentage());
//    		}
//    		if (project.getName() != null) {
//    			projectInDatabase.setName(project.getName());
//    		}
//    		if (project.getStatus() !=  null) {
//    			projectInDatabase.setStatus(project.getStatus());
//    		}
    	return todoItemRepository.save(todoItem);
//    	}
//        return null;
    }

	@PostMapping(value = "/todo", consumes = "application/json", produces = "application/json")
	public TodoItem postTodoItem(@RequestBody TodoItem todoItem) {
		todoItem.setStatus(1);
		return todoItemRepository.save(todoItem);
	}	
	
	@DeleteMapping(value = "/todo", consumes = "application/json", produces = "application/json")
	public void deleteTodo(@RequestBody TodoItem todoItem) {
		todoItemRepository.delete(todoItem);
	}
}
