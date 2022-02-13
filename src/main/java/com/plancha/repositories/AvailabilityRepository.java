package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

}
