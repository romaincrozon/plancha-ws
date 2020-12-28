package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plancha.dto.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

//    @Query(value = "SELECT p.name FROM plancha.project p where p.id = :id", nativeQuery = true) 
//    String findNameById(@Param("id") Long id);
}
