package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
