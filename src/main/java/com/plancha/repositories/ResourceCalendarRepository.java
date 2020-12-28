package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.plancha.dto.entity.ResourceCalendar;

public interface ResourceCalendarRepository extends JpaRepository<ResourceCalendar, Long> {
	



}
