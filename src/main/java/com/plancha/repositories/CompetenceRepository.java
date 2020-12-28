package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}
