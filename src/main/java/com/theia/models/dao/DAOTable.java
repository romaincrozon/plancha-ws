package com.theia.models.dao;

import java.util.List;

import com.theia.enums.TypeMedia;

public class DAOTable {

	private TypeMedia typeMedia;
	private String table;
	private List<String> mandatoryFields;
	
	public DAOTable(TypeMedia typeMedia, String table, List<String> mandatoryFields) {
		this.typeMedia = typeMedia;
		this.table = table;
		this.mandatoryFields = mandatoryFields;
	}

	public TypeMedia getTypeMedia() {
		return typeMedia;
	}

	public void setTypeMedia(TypeMedia typeMedia) {
		this.typeMedia = typeMedia;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<String> getMandatoryFields() {
		return mandatoryFields;
	}

	public void setMandatoryFields(List<String> mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}
}
