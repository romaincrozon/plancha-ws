package com.theia.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "theia-config")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ DatabaseConfig.class })
public class TheiaConfiguration {

	private static TheiaConfiguration theiaConfiguration;
	
    @XmlElement(name = "database")
	private DatabaseConfig databaseConfig;
    
    @XmlElement(name = "app")
	private AppConfig appConfig;
	
	public static TheiaConfiguration getInstance() {
		if (theiaConfiguration == null) {
			File configFile = new File("C:\\Users\\romai\\OneDrive\\Bureau\\theia-properties.xml");
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(TheiaConfiguration.class);
		        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		        theiaConfiguration = (TheiaConfiguration) jaxbUnmarshaller.unmarshal(configFile);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return theiaConfiguration;
	}
	
	public TheiaConfiguration() {}

	public DatabaseConfig getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}
	
	public static DatabaseConfig getDatabaseConfigInstance() {
		return TheiaConfiguration.getInstance().getDatabaseConfig();
	}

	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public static AppConfig getAppConfigInstance() {
		return TheiaConfiguration.getInstance().getAppConfig();
	}
}
