package com.theia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theia.beans.Media;
import com.theia.media.ws.MediaWS;

@Controller
public class GetMediaController {
	
	@RequestMapping(method = RequestMethod.GET, value="/media/allmedia")

	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Media> getMedia() {
		return MediaWS.getInstance().getMediaList();
	}

}
