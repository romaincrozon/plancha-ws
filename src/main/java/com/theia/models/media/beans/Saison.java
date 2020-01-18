package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.TypeMedia;
import com.theia.utils.FilesUtils;

public class Saison extends Media{

	private int nbEpisodes;
	private int idSerie;
	private int numSaison; //Numéro de la saison

	public Saison(ResultSet resultSet) {
		super(resultSet);
	}
	
	public Saison(File directory, Media parentMedia) {
		super(directory);
		if (parentMedia instanceof Serie)
			this.idSerie = parentMedia.getId();
		this.nbEpisodes = FilesUtils.getNumberOfFilesInDirectory(directory);
		this.numSaison = FilesUtils.getNumberInText(directory.getName());
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.saison);
	}
	
	public Saison(DavResource directory, Media parentMedia) {
		super(directory);
		if (parentMedia instanceof Serie)
			this.idSerie = parentMedia.getId();
		this.nbEpisodes = FilesUtils.getNumberOfFilesInDirectory(directory);
		this.numSaison = FilesUtils.getNumberInText(directory.getName());
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.saison);
	}
	
	public Saison(String title) {
		super(title);
	}

	@Override
	public void build(File directory) {
		super.build(directory);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.saison);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (nbEpisodes > 0) attributesMap.put("nb_episodes", String.valueOf(nbEpisodes));
		if (idSerie >= 0) attributesMap.put("serie_id", String.valueOf(idSerie));
		if (numSaison > 0) attributesMap.put("num_saison", String.valueOf(numSaison));
		return attributesMap;
	}
	
	public int getNbEpisode() {
		return nbEpisodes;
	}

	public void setNbEpisode(int nbEpisode) {
		this.nbEpisodes = nbEpisode;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public int getNumSaison() {
		return numSaison;
	}

	public void setNumSaison(int numSaison) {
		this.numSaison = numSaison;
	}
	
}
