package com.theia.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.sardine.DavResource;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.TypeMedia;
import com.theia.models.dao.DAOMedia;
import com.theia.models.dao.DAOUtils;
import com.theia.models.media.beans.Media;
import com.theia.services.SardineService;
import com.theia.utils.FilesUtils;
import com.theia.utils.MediaUtils;

@Controller
public class MediaController {
	
	@RequestMapping(value="/media/{typeMedia}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<Media> getAllMedias(@PathVariable("typeMedia") String typeMedia) {
		return DAOMedia.getAllMediaByType(TypeMedia.valueOf(typeMedia)); 
	}	

	@RequestMapping(value="/media/alphabetical/{typeMedia}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public Map<String, List<Media>> getAllMediasAlphabetical(@PathVariable("typeMedia") String typeMedia) {
		return DAOMedia.getAllMediaByTypeAlphabetical(TypeMedia.valueOf(typeMedia)); 
	}	

	@RequestMapping(value="/media/genres/{typeMedia}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<String> getAllGenres(@PathVariable("typeMedia") String typeMedia) {
		return DAOMedia.getAllGenres(TypeMedia.valueOf(typeMedia)); 
	}	
	
	@RequestMapping(value="/relatedFilms/genre/{genres}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<Media> getRelatedFilms(@PathVariable("genres") String genres) {
		return DAOMedia.getRelatedFilms(genres); 
	}	

	@RequestMapping(value="/media/{typeMedia}/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public Media getMediaById(@PathVariable("typeMedia") String typeMedia, @PathVariable("id") String id) {
		return MediaUtils.getMediaById(id, TypeMedia.valueOf(typeMedia));
	}	
	
	@RequestMapping(value="/media/all/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public Media getMediaById(@PathVariable("id") String id) {
		return MediaUtils.getMediaById(id);
	}	

	@RequestMapping(value="/media/randomMedia/{nbMedias}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<Media> getRandomNumberOfMedias(@PathVariable("nbMedias") int nbMedias) {
		return MediaUtils.getRandomNumberOfMedias(DAOMedia.getAllMedias(), nbMedias);
	}
	
	@RequestMapping(value="/media/topMedia/{typeMedia}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<Media> getTopMediasByType(@PathVariable("typeMedia") String typeMedia) {
		return MediaUtils.getTopMediasByType(TypeMedia.valueOf(typeMedia));
	}

	@RequestMapping(value="/media/refreshDatabase/{typeMedia}", method = RequestMethod.GET)
	public void refreshDatabase(@PathVariable("typeMedia") String typeMedia) {
		try {
			List<DavResource> davResouces = SardineService.getInstance().list("http://192.168.0.25/dav/Films/", 0);
			if (davResouces != null && !davResouces.isEmpty()) {
				DavResource davResource = davResouces.stream().findFirst().orElse(null);
				if (davResource != null) {
					Media media = new Media(davResource);
					FilesUtils.list(media);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/media/cleanDatabase/{typeMedia}", method = RequestMethod.GET)
	public void cleanDatabase(@PathVariable("typeMedia") String typeMedia) {
		try {
			DAOUtils.prepareTruncateQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.serie));
			DAOUtils.prepareTruncateQuery(TheiaConfiguration.getDatabaseConfigInstance().getDatabaseTableByTypeMedia(TypeMedia.episode));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
