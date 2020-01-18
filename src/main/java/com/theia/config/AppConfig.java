package com.theia.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement(name = "app")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ DatabaseTable.class })
public class AppConfig {

    @XmlElement(name = "top-min-value")
	private String topMinValue;

	public String getTopMinValue() {
		return topMinValue;
	}

	public void setTopMinValue(String topMinValue) {
		this.topMinValue = topMinValue;
	}
}
