package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.TypeMedia;
import com.theia.utils.FilesUtils;

public class Episode extends Media{

	private int idSaison;
	private int idSerie;
	private int numEpisode; //Numéro de l'épisode
	
	public Episode(ResultSet resultSet) {
		super(resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
	}

	public Episode(File file, Media media) {
		super(file);
		if (media instanceof Saison) {
			this.idSaison = media.getId();
			this.idSerie = ((Saison)media).getIdSerie();
		}else if (media instanceof Serie) {
			this.idSaison = 1;
			this.idSerie = ((Serie)media).getId();
		}
		this.numEpisode = FilesUtils.getNumberInText(file.getName());
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
	}
	
	public Episode(DavResource resource, Media media) {
		super(resource);
		if (media instanceof Saison) {
			this.idSaison = media.getId();
			this.idSerie = ((Saison)media).getIdSerie();
		}else if (media instanceof Serie) {
			this.idSaison = 1;
			this.idSerie = ((Serie)media).getId();
		}
		this.numEpisode = FilesUtils.getNumberInText(resource.getName());
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
	}
	
	@Override
	public void build(File file) {}

	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (idSaison >= 0) attributesMap.put("saison_id", String.valueOf(idSaison));
		if (idSerie >= 0) attributesMap.put("serie_id", String.valueOf(idSerie));
		if (numEpisode > 0) attributesMap.put("num_episode", String.valueOf(numEpisode));
		return attributesMap;
	}
	
	public int getIdSaison() {
		return idSaison;
	}

	public void setIdSaison(int idSaison) {
		this.idSaison = idSaison;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public int getNumEpisode() {
		return numEpisode;
	}

	public void setNumEpisode(int numEpisode) {
		this.numEpisode = numEpisode;
	}

}
