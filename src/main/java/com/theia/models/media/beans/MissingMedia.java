package com.theia.models.media.beans;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.theia.config.DatabaseTable;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOUtils;

public class MissingMedia {

	private String title;
	private String url;
	private String type;
	private DatabaseTable table;
	
	
	public MissingMedia(String title, String url, String type) {
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.missingMedia);
		this.title = title;
		this.url = url;
		this.type = type;
	}
	
	public MissingMedia(ResultSet resultSet) {
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.missingMedia);
		this.title = DAOUtils.getFieldStringValue(resultSet, Fields.MISSINGMEDIA_PREFIX + Fields.TITRE);
		this.url = DAOUtils.getFieldStringValue(resultSet, Fields.MISSINGMEDIA_PREFIX + Fields.URL);
		this.type = DAOUtils.getFieldStringValue(resultSet, Fields.MISSINGMEDIA_PREFIX + Fields.TYPE);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = new HashMap<String, String>();
		String prefix = table.getType().name() + "_";
		
		if (title != null) attributesMap.put(prefix + Fields.TITRE, title);
		if (url != null) attributesMap.put(prefix + Fields.URL, url);
		if (type != null) attributesMap.put(prefix + Fields.TYPE, type);
		return attributesMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DatabaseTable getTable() {
		return table;
	}

	public void setTable(DatabaseTable table) {
		this.table = table;
	}
}
