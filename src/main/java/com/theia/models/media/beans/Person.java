package com.theia.models.media.beans;

import java.util.HashMap;
import java.util.Map;

public class Person {

	private String name;
	
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = new HashMap<String, String>();
		if (name != null) attributesMap.put("name", name);
		return attributesMap;
	}
}
