package com.theia.config;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class DatabaseTables {

    @XmlElement(name = "table")
	private List<DatabaseTable> tables;

	public List<DatabaseTable> getDatabaseTables() {
		return tables;
	}

	public void setDatabaseTables(List<DatabaseTable> tables) {
		this.tables = tables;
	}
}
