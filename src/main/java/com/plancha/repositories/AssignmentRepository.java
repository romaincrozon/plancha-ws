package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
