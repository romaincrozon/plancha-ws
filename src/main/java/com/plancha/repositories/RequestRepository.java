package com.plancha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plancha.dto.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
//	List<Request> findByProjectId(Long projectId);
//	
//    @Query(value = "SELECT request.*, project.name FROM plancha.request request INNER JOIN plancha.project project on request.project_id = project.id", nativeQuery = true) 
//	List<Request> findAllWithProjectName();
}
