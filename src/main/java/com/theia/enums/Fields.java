package com.theia.enums;

public enum Fields {

	ID ("id"),
	TITRE ("title"),
	YEAR ("year"),
	RATED("rated"),
	RELEASED ("released"),
	RUNTIME ("runtime"),
	GENRE ("genre"),
	DIRECTOR ("director"),
	WRITER ("writer"),
	ACTORS ("actors"), 
	PLOT ("plot"),
	LANGUAGE ("language"),
	COUNTRY ("country"),
	AWARDS ("awards"),
	POSTER ("poster"),
	RATINGS ("ratings"),
	METASCORE ("metascore"),
	IMDBRATING ("imdbRating"),
	IMDBVOTES ("imdbVotes"),
	IMDBID ("imdbId"),
	
	//Specifique episode
	SAISON_ID ("saison_id"),
	SERIE_ID ("serie_id"),
	NUM_EPISODE ("num_episode"),

	//Specifique film
	BOX_OFFICE ("box_office"),
	PRODUCTION ("production"),
	
	//Specifique serie
	NB_SAISONS ("nbSaisons"),

	//Specifique saison
	NB_EPISODES ("nbEpisodes"),
	NUM_SAISON ("num_saison");
	
	private String field = "";
	   
  	private Fields(String field){
  		this.field = field;
  	}
  	
  	public String toString() {
  		return field;
  	}
}
