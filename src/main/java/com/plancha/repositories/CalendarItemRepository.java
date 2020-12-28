package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.CalendarItem;

public interface CalendarItemRepository extends JpaRepository<CalendarItem, Long> {
	



}
