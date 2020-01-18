package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.theia.config.TheiaConfiguration;
import com.theia.enums.TypeMedia;
import com.theia.utils.FilesUtils;

public class Serie extends Media{

	private int nbSaisons;

	public Serie(ResultSet resultSet) {
		super(resultSet);
        try {
			this.setNbSaisons(resultSet.getInt("nbSaisons"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}
	
	public Serie(File directory) {
		super(directory);
		this.nbSaisons = FilesUtils.getNumberOfSubDirectories(directory);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}

	@Override
	public void build(File file) {
		super.build(file);
		this.nbSaisons = FilesUtils.getNumberOfSubDirectories(file);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (this.nbSaisons > 0) attributesMap.put("nbSaisons", String.valueOf(nbSaisons));
		return attributesMap;
	}

	public int getNbSaisons() {
		return nbSaisons;
	}

	public void setNbSaisons(int nbSaisons) {
		this.nbSaisons = nbSaisons;
	}	
}
