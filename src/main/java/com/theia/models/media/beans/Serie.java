package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOUtils;
import com.theia.utils.FilesUtils;

public class Serie extends Media{

	private int totalSeasons;
	private List<Saison> seasons = new ArrayList<Saison>();

	public Serie() {
		super();
	}
	
	public Serie(ResultSet resultSet) {
		super(Fields.SERIE_PREFIX, resultSet);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
		this.type = Fields.SERIE;
		this.setTotalSeasons(DAOUtils.getFieldIntValue(resultSet, Fields.SERIE_PREFIX + Fields.TOTAL_SEASONS));
	}
	
	public Serie(File directory) {
		super(directory);
		this.totalSeasons = FilesUtils.getNumberOfSubDirectories(directory);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}

	@Override
	public void build(File file) {
		super.build(file);
		this.totalSeasons = FilesUtils.getNumberOfSubDirectories(file);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}
	
	@Override
	public void build(DavResource resource) {
		super.build(resource);
		this.table = TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie);
	}
	
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = super.getAttributesMap();
		String prefix = table.getType().name() + "_";
		
		if (this.totalSeasons > 0) attributesMap.put(prefix + Fields.TOTAL_SEASONS, String.valueOf(totalSeasons));
		return attributesMap;
	}

	public int getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(int totalSeasons) {
		this.totalSeasons = totalSeasons;
	}

	public List<Saison> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Saison> seasons) {
		this.seasons = seasons;
	}	
}
