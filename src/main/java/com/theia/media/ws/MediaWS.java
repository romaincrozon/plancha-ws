package com.theia.media.ws;

public class MediaWS {
	
	private static MediaWS mediaWS = null;
	
	private MediaWS(){}
	
	public static MediaWS getInstance() {
		
		if(mediaWS == null) {
			mediaWS = new MediaWS();
		}
		return mediaWS;
	}
}
