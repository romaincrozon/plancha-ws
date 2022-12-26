package com.plancha.repositories;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plancha.dto.DayItem;
import com.plancha.dto.entity.ResourceCalendar;

public interface ResourceCalendarRepository extends JpaRepository<ResourceCalendar, Long> {
	
	public Optional<ResourceCalendar> findByProjectIdAndResourceId(final Long projectId, final Long resourceId);
	
//	@Query("SELECT new com.plancha.dto.ResourceCalendar(rc.id, ci.calendar, ci.value, rc.id) "
//			+ "FROM CalendarItem as ci "
//			+ "INNER JOIN ResourceCalendar as rc " 
//			+ "ON ci.resourceCalendar.id = rc.id "
//			+ "WHERE ci.calendar BETWEEN :startDate and :endDate "
//			+ "ORDER BY rc.task.id, rc.resource.id")
//	List<ResourceCalendar> findResourceCalendarsByDate(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);
	
	@Query("SELECT rc "
			+ "FROM ResourceCalendar as rc " 
			+ "LEFT JOIN CalendarItem as ci "
			+ "ON ci.resourceCalendar.id = rc.id "
			+ "WHERE ci.calendar BETWEEN :startDate and :endDate "
			+ "AND rc.project.id = :projectId "
			+ "ORDER BY rc.resource.id")
	List<ResourceCalendar> findResourceCalendarByDateAndProject(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate, @Param("projectId") Long projectId);
	


}
