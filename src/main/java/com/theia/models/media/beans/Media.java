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
import com.theia.enums.TypeMedia;

public class Media {

	protected int id;
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

    @JsonIgnore
	protected List<Media> medias;

	public Media (ResultSet resultSet) {
        try {
			this.setTitle(resultSet.getString("title"));
	        this.setYear(resultSet.getString("year"));
	        this.setRated(resultSet.getString("rated"));
	        this.setReleased(resultSet.getString("released"));
	        this.setRuntime(resultSet.getString("runtime"));
	        this.setGenre(resultSet.getString("genre"));
	        this.setDirector(resultSet.getString("director"));
	        this.setWriter(resultSet.getString("writer"));
	        this.setYear(resultSet.getString("actors"));
	        this.setPlot(resultSet.getString("plot")); 
	        this.setLanguage(resultSet.getString("language")); 
	        this.setCountry(resultSet.getString("country")); 
	        this.setAwards(resultSet.getString("awards")); 
	        this.setPoster(resultSet.getString("poster")); 
	        this.setYear(resultSet.getString("ratings")); 
	        this.setMetascore(resultSet.getString("metascore")); 
	        this.setImdbRating(resultSet.getString("imdbRating")); 
	        this.setImdbVotes(resultSet.getString("imdbVotes")); 
	        this.setImdbID(resultSet.getString("imdbId")); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		
		if (title != null) attributesMap.put("title", title);
		if (year != null) attributesMap.put("year", year);
		if (rated != null) attributesMap.put("rated", rated);
		if (released != null) attributesMap.put("released", released);
		if (runtime != null) attributesMap.put("runtime", runtime);
		if (genre != null) attributesMap.put("genre", genre);
//		if (director != null) attributesMap.put("director", director);
//		if (writer != null) attributesMap.put("writer", writer);
//		if (CollectionUtils.isEmpty(actors)) attributesMap.put("actors", actors); 
		if (plot != null) attributesMap.put("plot", plot);
		if (language != null) attributesMap.put("language", language);
		if (country != null) attributesMap.put("country", country);
		if (awards != null) attributesMap.put("awards", awards);
		if (poster != null) attributesMap.put("poster", poster);
//		if (CollectionUtils.isEmpty(ratings)) attributesMap.put("ratings", ratings);
		if (metascore != null) attributesMap.put("metascore", metascore);
		if (imdbRating != null) attributesMap.put("imdbRating", imdbRating);
		if (imdbVotes != null) attributesMap.put("imdbVotes", imdbVotes.replace(",", ""));
		if (imdbID != null) attributesMap.put("imdbId", imdbID);
		
		return attributesMap;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}
