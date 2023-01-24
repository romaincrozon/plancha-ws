package com.plancha.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plancha.dto.entity.Project;

public class Utils {
	
	static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static Object mapJSONToDTO(JSONObject json, Class dtoClass) {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			return mapper.readValue(json.toString(), dtoClass);
		} catch (Exception e) {
			logger.error("Error parsing JSON for class:" + dtoClass.getName());
		}
		return null;
	}

	public static List<String> replaceValuesInMap(Map<String, String> map){
		return map.values().stream().map(value -> value.replace("'", "''")).collect(Collectors.toList());
	}

	public static void mapProjects(List<Project> allProjects, List<Project> projects) {
		projects.forEach(project -> {
			allProjects.add(project);
			mapProjects(allProjects, project.getProjects());
		});
	}
	
	
//	public static Status getDefaultStatus(StatusRepository statusRepository) {
//		Status defaultStatus = statusRepository.findByDefaultStatus(true);
//		if (defaultStatus == null) {
//			final List<Status> statusList = statusRepository.findAll();
//			return !statusList.isEmpty() ? statusList.get(0) : null;
//		}
//		return defaultStatus;
//	}	
}
