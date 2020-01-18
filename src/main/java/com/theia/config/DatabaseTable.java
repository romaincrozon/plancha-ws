package com.theia.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.theia.enums.TypeMedia;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseTable {

    @XmlElement(name = "type")
	private TypeMedia type;

    @XmlElement(name = "name")
	private String name;

    @XmlElement(name = "mandatory-fields")
	private String mandatoryFields;
	
	public TypeMedia getType() {
		return type;
	}
	public void setType(TypeMedia typeMedia) {
		this.type = typeMedia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMandatoryFields() {
		return mandatoryFields;
	}
	public void setMandatoryFields(String mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}
}
