package com.plancha.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.SubProject;

public interface SubProjectRepository extends JpaRepository<SubProject, Long> {

}
