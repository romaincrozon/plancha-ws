package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOUtils;
import com.theia.utils.FilesUtils;
import com.theia.utils.Utils;

public class Episode extends Media{

	@JsonProperty("Season")
	private int numSaison;
	
	@JsonProperty("seriesID")
	private String idSerie;
	
	@JsonProperty("Episode")
	private int numEpisode; //Numéro de l'épisode

	public Episode() {
		super();
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
	}
	
	public Episode(ResultSet resultSet) {
		super(Fields.EPISODE, resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode);
		this.numEpisode = DAOUtils.getFieldIntValue(resultSet, Fields.EPISODE + Fields.NUM_EPISODE);
		this.numSaison = DAOUtils.getFieldIntValue(resultSet, Fields.EPISODE + Fields.NUM_SAISON);
		this.idSerie = DAOUtils.getFieldStringValue(resultSet, Fields.EPISODE + Fields.ID);
		this.imdbID = DAOUtils.getFieldStringValue(resultSet, Fields.EPISODE + Fields.ID.toString());
	}

	@Override
	public void build(File file) {}

	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		if (numSaison > 0) attributesMap.put(Fields.NUM_SAISON.toString(), String.valueOf(numSaison));
		if (!Utils.isValueNull(idSerie)) attributesMap.put(Fields.SERIE_ID.toString(), idSerie);
		if (numEpisode > 0) attributesMap.put(Fields.NUM_EPISODE.toString(), String.valueOf(numEpisode));
		return attributesMap;
	}
	
	public int getNumSaison() {
		return numSaison;
	}

	public void setNumSaison(int numSaison) {
		this.numSaison = numSaison;
	}

	public String getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(String idSerie) {
		this.idSerie = idSerie;
	}

	public int getNumEpisode() {
		return numEpisode;
	}

	public void setNumEpisode(int numEpisode) {
		this.numEpisode = numEpisode;
	}

}
