package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
