package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

import com.theia.config.DatabaseTable;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.utils.Utils;

public class Film extends Media{

	private String boxOffice;
	private String production;
	
	public Film(File file) { 
		super(file); 
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}
	
	public Film(ResultSet resultSet) {
		super(Fields.FILM, resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}

	@Override
	public void build(File file) {
		super.build(file);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.film);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (!Utils.isValueNull(boxOffice)) attributesMap.put("box_office", String.valueOf(boxOffice));
		if (!Utils.isValueNull(production)) attributesMap.put("production", String.valueOf(production));
		return attributesMap;
	}

}
