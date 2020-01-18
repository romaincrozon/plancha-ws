package com.theia.models.media.beans;

import java.util.HashMap;
import java.util.Map;

public class Rating {

	private String source;
	private String value;
	
	public Rating(String source, String value) {
		this.source = source;
		this.value = value;
	}

	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = new HashMap<String, String>();
		if (source != null) attributesMap.put("name", source);
		if (value != null) attributesMap.put("name", value);
		return attributesMap;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
