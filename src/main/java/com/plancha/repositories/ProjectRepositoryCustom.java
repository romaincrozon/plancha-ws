package com.plancha.repositories;

import java.util.Calendar;
import java.util.List;

import com.plancha.dto.entity.Project;

public interface ProjectRepositoryCustom {

    List<Project> findProjectByDate(Calendar begin, Calendar end);

}