package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

import com.theia.config.TheiaConfiguration;
import com.theia.enums.TypeMedia;

public class Film extends Media{

	private String boxOffice;
	private String production;
	
	public Film(File file) { 
		super(file); 
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}
	
	public Film(ResultSet resultSet) {
		super(resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}

	@Override
	public void build(File file) {
		super.build(file);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (boxOffice != null) attributesMap.put("box_office", String.valueOf(boxOffice));
		if (production != null) attributesMap.put("production", String.valueOf(production));
		return attributesMap;
	}

}
