package com.plancha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Need;

public interface NeedRepository extends JpaRepository<Need, Long> {

	List<Need> findByIsProjectNeed(boolean isProjectNeed);
	List<Need> findByProjectId(Long idProject);
}
