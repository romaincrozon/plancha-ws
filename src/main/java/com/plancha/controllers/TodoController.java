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

import com.plancha.dto.entity.TodoItem;
import com.plancha.repositories.TodoItemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@GetMapping(value = "/todo", produces = "application/json")
	public List<TodoItem> getAllTodoItems() {
		return todoItemRepository.findAll();
	}
	
	@GetMapping(value = "/todo/{idResource}", produces = "application/json")
	public TodoItem getTodoItem(@PathVariable Long idTodoItem) {
		return todoItemRepository.findById(idTodoItem).orElse(null);
	}	

	@PostMapping(value = "/todo", consumes = "application/json", produces = "application/json")
	public TodoItem postTodoItem(@RequestBody TodoItem todoItem) {
		return todoItemRepository.save(todoItem);
	}	
	
	@DeleteMapping(value = "/todo", consumes = "application/json", produces = "application/json")
	public void deleteTodo(@RequestBody TodoItem todoItem) {
		todoItemRepository.delete(todoItem);
	}	
		
}
