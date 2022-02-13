package com.plancha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
	
	public List<TodoItem> findAllByOrderByStatusAsc();   

}
