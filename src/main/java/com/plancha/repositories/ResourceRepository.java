package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
//
////	@Query(value = "SELECT * FROM plancha.resource r " + 
////	  		"INNER JOIN plancha.resource_calendar rc " + 
////	  		"ON r.id = rc.resource_id " + 
////	  		"INNER JOIN plancha.task t " + 
////	  		"ON rc.task_id = t.id " + 
////	  		"INNER JOIN plancha.calendar_item c " + 
////	  		"ON rc.task_id = t.id " + 
////	  		"where t.id = 14 " + 
////	  		"and c.calendar BETWEEN '2020-11-21' and '2020-11-28'", nativeQuery = true) 
//	@Query("SELECT new com.plancha.bean.PlanningResourceEntry(p.id, p.name, sp.id, sp.name, t.name, t.id, r.firstname, r.lastname, "
//			+ "r.id, c.calendar, c.value) FROM plancha.project p " + 
//			"INNER JOIN plancha.sub_project sp " + 
//			"ON p.id = sp.project_id " + 
//			"INNER JOIN plancha.task t " + 
//			"ON sp.id = t.sub_project_id " + 
//			"INNER JOIN plancha.resource_calendar rc " + 
//			"ON t.id = rc.task_id " + 
//			"INNER JOIN plancha.resource r " + 
//			"ON rc.resource_id = r.id " + 
//			"INNER JOIN plancha.calendar_item c " + 
//			"ON rc.id = c.resource_calendar_id " + 
//			"where t.id = 14 " + 
//			"and c.calendar BETWEEN '2020-11-21' and '2020-11-28'") 
//	  List<PlanningResourceEntry> findResourceCalendarByRange();
	  


}
