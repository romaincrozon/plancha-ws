package com.theia.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.util.CollectionUtils;

import com.theia.enums.TypeMedia;


@XmlRootElement(name = "database")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ DatabaseTable.class })
public class DatabaseConfig {

    @XmlElement(name = "url")
	private String url;
    
    @XmlElement(name = "name")
	private String name;
    
    @XmlElement(name = "user")
	private String user;
    
    @XmlElement(name = "password")
	private String password;
    
    @XmlElement(name = "tables")
	private DatabaseTables tables;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DatabaseTables getTables() {
		return tables;
	}
	public void setTables(DatabaseTables tables) {
		this.tables = tables;
	}
	public DatabaseTable getDatabaseTableByTypeMedia(TypeMedia typeMedia) {
		if (tables != null && !CollectionUtils.isEmpty(tables.getDatabaseTables())){
			return tables.getDatabaseTables().stream()
			        .filter(table -> table.getType().equals(typeMedia))
			        .findFirst().orElse(null);
		}
		return null;
	}
}
