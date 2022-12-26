package com.plancha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plancha.dto.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

//    @Query(value = "SELECT p.name FROM plancha.project p where p.id = :id", nativeQuery = true) 
    List<Project> findByParent(@Param("parent") Integer parent);

    @Query(value = "SELECT p.id, p.name FROM plancha.project p ", nativeQuery = true) 
    List<Project> findAll();
	
	
}
