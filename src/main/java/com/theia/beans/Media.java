package com.theia.beans;

import org.json.simple.JSONObject;

public class Media {

	private static final String TITLE = "Title";
	private static final String YEAR = "Year";
	private static final String RELEASED = "Released";
	private static final String GENRE = "Genre";
	private static final String WRITER = "Writer";
	private static final String ACTORS = "Actors";
	private static final String PLOT = "Plot";
	private static final String POSTER = "Poster";
	private static final String IMDBRATING = "imdbRating";
	private static final String IMDBID = "imdbID";
	private static final String TYPE = "Type";
	private static final String TOTALSEASONS = "totalSeasons";

	String title;
	String year;
	String released;
	String genre;
	String writer;
	String actors;
	String plot;
	String poster;
	String imdbRating;
	String id;
	String type;
	String totalSeasons;

	public Media(JSONObject jsonMedia) {
		this.title = (String) jsonMedia.get(TITLE);
		this.year = (String) jsonMedia.get(YEAR);
		this.released = (String) jsonMedia.get(RELEASED);
		this.genre = (String) jsonMedia.get(GENRE);
		this.writer = (String) jsonMedia.get(WRITER);
		this.actors = (String) jsonMedia.get(ACTORS);
		this.plot = (String) jsonMedia.get(PLOT);
		this.poster = (String) jsonMedia.get(POSTER);
		this.imdbRating = (String) jsonMedia.get(IMDBRATING);
		this.id = (String) jsonMedia.get(IMDBID);
		this.type = (String) jsonMedia.get(TYPE);
		this.totalSeasons = (String) jsonMedia.get(TOTALSEASONS);
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

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(String totalSeasons) {
		this.totalSeasons = totalSeasons;
	}
}
