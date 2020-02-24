package com.theia.models.media.beans;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sardine.DavResource;
import com.theia.config.DatabaseTable;
import com.theia.enums.Fields;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOUtils;
import com.theia.utils.Utils;

public class Media {

    @JsonIgnore
	protected DatabaseTable table;

	@JsonProperty("Title")
	protected String title;

	@JsonProperty("Year")
	protected String year; 
	
	@JsonProperty("Rated")
	protected String rated;
	
	@JsonProperty("Released")
	protected String released;

	@JsonProperty("Runtime")
	protected String runtime;

	@JsonProperty("Genre")
	protected String genre; 

	@JsonProperty("Director")
	protected String director;

	@JsonProperty("Writer") 
	protected String writer;
	
	protected List<Person> actors;
	
	@JsonProperty("Plot")
	protected String plot;
	
	@JsonProperty("Language")
	protected String language;
	@JsonProperty("Country")
	protected String country; 
	@JsonProperty("Awards")
	protected String awards; 
	@JsonProperty("Poster")
	protected String poster;  
	
	protected List<Rating> ratings;  
	
	@JsonProperty("Metascore")
	protected String metascore;
	
	protected String imdbRating;
	protected String imdbVotes;
	protected String imdbID;

	protected File file;
	protected DavResource resource;
	
	protected String url;
	
	protected String type;

    @JsonIgnore
	protected List<Media> medias;

	public Media () {}
    
	public Media (String prefix, ResultSet resultSet) {
		this.setTitle(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.TITRE));
        this.setYear(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.YEAR));
        this.setRated(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.RATED));
        this.setReleased(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.RELEASED));
        this.setRuntime(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.RUNTIME));
        this.setGenre(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.GENRE));
        this.setDirector(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.DIRECTOR));
        this.setWriter(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.WRITER));
        this.setYear(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.ACTORS));
        this.setPlot(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.PLOT)); 
        this.setLanguage(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.LANGUAGE)); 
        this.setCountry(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.COUNTRY)); 
        this.setAwards(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.AWARDS)); 
        this.setPoster(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.POSTER)); 
        this.setYear(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.RATINGS)); 
        this.setMetascore(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.METASCORE)); 
        this.setImdbRating(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.IMDBRATING)); 
        this.setImdbVotes(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.IMDBVOTES)); 
        this.setImdbID(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.ID)); 
        this.setUrl(DAOUtils.getFieldStringValue(resultSet, prefix + Fields.URL)); 
	}

	public Media (File file) {
    	this(file.getName());
		this.file = file;
	}
	
	public Media (DavResource resource) {
    	this(resource.getName());
		this.resource = resource;
	}
    
    public Media (String title) {
    	this.title = FilenameUtils.removeExtension(title);
    }

    public void build(File file) {
    	this.file = file;
    }
    
    public void build(DavResource resource) {
    	this.resource = resource;
    	this.url = resource.getHref().toString();
    }
    
	public void addMedia(Media media) {
		if (medias == null) {
			medias = new ArrayList<Media>();
		}
		medias.add(media);
	}

    @JsonIgnore
	public Map<String, String> getAttributesMap(){
		Map<String, String> attributesMap = new HashMap<String, String>();
		String prefix = table.getType().name() + "_";
		
		if (!Utils.isValueNull(title)) attributesMap.put(prefix + Fields.TITRE, title);
		if (!Utils.isValueNull(year)) attributesMap.put(prefix + Fields.YEAR, year);
		if (!Utils.isValueNull(rated)) attributesMap.put(prefix + Fields.RATED, rated);
		
		String releasedDate = Utils.getSQLDate(released);
		if (releasedDate != null) attributesMap.put(prefix + Fields.RELEASED, releasedDate);
		
		if (!Utils.isValueNull(runtime)) attributesMap.put(prefix + Fields.RUNTIME, runtime);
		if (!Utils.isValueNull(genre)) attributesMap.put(prefix + Fields.GENRE, genre);
		if (!Utils.isValueNull(plot)) attributesMap.put(prefix + Fields.PLOT, plot);
		if (!Utils.isValueNull(language)) attributesMap.put(prefix + Fields.LANGUAGE, language);
		if (!Utils.isValueNull(country)) attributesMap.put(prefix + Fields.COUNTRY, country);
		if (!Utils.isValueNull(awards)) attributesMap.put(prefix + Fields.AWARDS, awards);
		if (!Utils.isValueNull(poster)) attributesMap.put(prefix + Fields.POSTER, poster);
		if (!Utils.isValueNull(metascore)) attributesMap.put(prefix + Fields.METASCORE, metascore);
		if (!Utils.isValueNull(imdbRating)) attributesMap.put(prefix + Fields.IMDBRATING, imdbRating);
		if (!Utils.isValueNull(imdbVotes)) attributesMap.put(prefix + Fields.IMDBVOTES, imdbVotes.replace(",", ""));
		if (!Utils.isValueNull(imdbID)) attributesMap.put(prefix + Fields.ID, imdbID);
		if (!Utils.isValueNull(url)) attributesMap.put(prefix + Fields.URL, url);
		
		return attributesMap;
	}

    @JsonIgnore
	public DatabaseTable getDatabaseTable() {
		return table;
	}

	public void setDatabaseTable(DatabaseTable table) {
		this.table = table;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String Released) {
		this.released = Released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbId) {
		this.imdbID = imdbId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
    
	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public DatabaseTable getTable() {
		return table;
	}

	public void setTable(DatabaseTable table) {
		this.table = table;
	}

	public DavResource getResource() {
		return resource;
	}

	public void setResource(DavResource resource) {
		this.resource = resource;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
