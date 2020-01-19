package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOUtils;
import com.theia.utils.FilesUtils;
import com.theia.utils.Utils;

public class Saison extends Media{
	
	@JsonProperty("totalSeasons")
	private int totalSeasons;
	
	@JsonProperty("Season")
	private int season;
	
	@JsonProperty("Episodes")
	private List<Episode> episodes = new ArrayList<Episode>();
	
	private String idSerie;
	private int numSaison; //Numéro de la saison

	public int getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(int totalSeasons) {
		this.totalSeasons = totalSeasons;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public Saison() {
		super();
	}
	
	public Saison(ResultSet resultSet) {
		super(Fields.SAISON, resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.saison);
		this.season = DAOUtils.getFieldIntValue(resultSet, Fields.EPISODE + Fields.NUM_SAISON);
	}
	
	public Saison(File directory, Media parentMedia) {
		super(directory);
		if (parentMedia instanceof Serie)
			this.idSerie = parentMedia.getImdbID();
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.saison);
	}
	
	public Saison(DavResource directory, Media parentMedia) {
		super(directory);
		if (parentMedia instanceof Serie)
			this.idSerie = parentMedia.getImdbID();
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
		if (CollectionUtils.isEmpty(episodes)) attributesMap.put("nb_episodes", String.valueOf(episodes.size()));
		if (!Utils.isValueNull(idSerie)) attributesMap.put("serie_id", idSerie);
		if (numSaison > 0) attributesMap.put("num_saison", String.valueOf(numSaison));
		return attributesMap;
	}
	
	public String getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(String idSerie) {
		this.idSerie = idSerie;
	}

	public int getNbEpisodes() {
		if (!CollectionUtils.isEmpty(episodes)) {
			return episodes
				.stream()
				.mapToInt(episode -> episode.getNumEpisode())
				.max()
				.orElse(-1);
		}
		return -1;
	}	
}
