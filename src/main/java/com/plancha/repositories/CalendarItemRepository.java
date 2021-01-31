package com.plancha.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plancha.dto.DayItem;
import com.plancha.dto.entity.CalendarItem;

public interface CalendarItemRepository extends JpaRepository<CalendarItem, Long> {
	
	@Query("SELECT new com.plancha.dto.DayItem(ci.id, ci.calendar, ci.value, rc.id) "
			+ "FROM CalendarItem as ci "
			+ "INNER JOIN ResourceCalendar as rc " 
			+ "ON ci.resourceCalendar.id = rc.id "
			+ "WHERE ci.calendar BETWEEN :startDate and :endDate "
			+ "ORDER BY rc.task.id, rc.resource.id")
	List<DayItem> findCalendarItemsByDate(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);
	


}
