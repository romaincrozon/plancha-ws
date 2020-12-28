package com.plancha.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
