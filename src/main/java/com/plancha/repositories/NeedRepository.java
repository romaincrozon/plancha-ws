package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Need;

public interface NeedRepository extends JpaRepository<Need, Long> {

}
