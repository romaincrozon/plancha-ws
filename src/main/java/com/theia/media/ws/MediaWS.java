package com.theia.media.ws;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.theia.beans.Media;
import com.theia.imdb.OMDbWS;
import com.theia.imdb.OMDbWSImpl;

public class MediaWS {
	
	private List<Media> mediaList;
	
	private static MediaWS mediaWS = null;
	
	private MediaWS(){
		mediaList = new ArrayList<Media>();
	}
	
	public static MediaWS getInstance() {
		
		if(mediaWS == null) {
			mediaWS = new MediaWS();
		}
		return mediaWS;
	}
	
	public List<Media> getMediaList() {
		OMDbWS omdbWS = new OMDbWSImpl();
		JSONObject jsonMedia = omdbWS.getMediaFromTitre("Brooklyn Nine-Nine");
		Media media = new Media(jsonMedia);
		mediaList = new ArrayList<Media>();
		mediaList.add(media);
		return mediaList;
	}

}
