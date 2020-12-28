package com.plancha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plancha.dto.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
