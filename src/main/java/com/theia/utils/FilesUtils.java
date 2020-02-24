package com.theia.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

import com.github.sardine.DavResource;
import com.theia.enums.TypeMedia;
import com.theia.imdb.OMDbUtils;
import com.theia.models.dao.DAOMedia;
import com.theia.models.media.beans.Episode;
import com.theia.models.media.beans.Film;
import com.theia.models.media.beans.Media;
import com.theia.models.media.beans.MissingMedia;
import com.theia.models.media.beans.Saison;
import com.theia.models.media.beans.Serie;
import com.theia.services.SardineService;

public class FilesUtils {
	
	public static void list(Media media) {
//		listSeriesWebdav(media);
		listMoviesWebdav(media);
	}
	
//	public static void listFiles(TypeMedia typeMedia, Media parentMedia) {
//		File[] files = parentMedia.getFile().listFiles();
//		Media media = null;
//		for (File file : files) {
//			if (file.isDirectory()) { 
//				if (file.getName().startsWith("Saison")) {
//					media = new Saison(file, parentMedia);
//					System.out.println("Saison " + file.getName());
//				}else {
//					media = (Serie)OMDbUtils.getOmdbMedia(file.getName(), Serie.class);
//					media.build(file);
//					System.out.println("Serie " + file.getName());
//				}
//				int mediaId = DAOMedia.insertMedia(media);
//				media.setId(mediaId);
//				listFiles(typeMedia, media);
//				parentMedia.addMedia(media);
//			} else if (FilesUtils.isVideo(file)){
//				if (typeMedia == TypeMedia.serie) {
//					media = new Episode(file, parentMedia);
//					System.out.println("Episode " + file.getName());
//					parentMedia.addMedia(media);
//				}else {
//					media = (Film)OMDbUtils.getOmdbMedia(FilenameUtils.removeExtension(file.getName()), Film.class);
//					media.build(file);
//					System.out.println("Film " + file.getName());
//					parentMedia.addMedia(media);
//				}
//				DAOMedia.insertMedia(media);
//			}
//		}
//	}
	
	public static boolean isSameResource(DavResource davResource1, DavResource davResource2) {
		return davResource1.getPath().equals(davResource2.getPath() + "/") 
				|| davResource2.getPath().equals(davResource1.getPath() + "/") ;
		
	}
	
	public static void insertSerieItems(Serie serie) {
		Map<Integer, String> saisonsUrl = getResourcesNumbers(serie);
		for (int numSaison = 1; numSaison <= serie.getTotalSeasons(); numSaison++) {
		Saison saison = (Saison)OMDbUtils.getOmdbSaison(Saison.class, serie.getImdbID(), numSaison);
			if (saison != null) {
				Map<Integer, String> episodesUrl = new HashMap<Integer, String>();
				if (saisonsUrl.containsKey(numSaison)) {
					saison.setUrl(saisonsUrl.get(numSaison));
					episodesUrl = getResourcesNumbers(saison);
				}
				for(int numEpisode = 1; numEpisode <= saison.getNbEpisodes(); numEpisode++) {
					Episode episode = (Episode)OMDbUtils.getOmdbEpisode(Episode.class, serie.getImdbID(), numSaison, numEpisode);
					if (episode != null) {
						if (episodesUrl.containsKey(numEpisode)) {
							episode.setUrl(episodesUrl.get(numEpisode));
						}
						DAOMedia.insertMedia(episode);
					}
				}
			}
		}
	}
	
	public static Map<Integer, String> getResourcesNumbers(Media media) {
		try {
			Map<Integer, String> resourcesMap = new HashMap<Integer, String>();
			SardineService.getInstance().list(media.getUrl(), 1)
				.stream()
				.forEach(davResource -> {
					int num = FilesUtils.getNumberInText(davResource.getName());
					if (!davResource.getHref().toString().equals(media.getUrl()) 
							&& !davResource.getHref().toString().equals(media.getUrl() + "/") 
							&& num >= 0) {
						resourcesMap.put(num, davResource.getHref().toString());
					}
				});
			return resourcesMap;
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void listSeriesWebdav(Media media) {
		try {
			List<DavResource> resources = SardineService.getInstance().list(media.getResource().getHref().toString(), 1);
			for (DavResource resource : resources) {
				if (resource.isDirectory() 
						&& !resource.getName().startsWith("Saison")
						&& DAOMedia.isMediaExistsInDatabase(TypeMedia.serie, resource.getHref().getPath())) {
					Serie serie = (Serie)OMDbUtils.getOmdbSerie(resource.getName(), Serie.class);
					if (serie != null) {
						serie.build(resource);
						System.out.println("Serie " + resource.getName());
						DAOMedia.insertMedia(serie);
						insertSerieItems(serie);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listMoviesWebdav(Media media) {
		try {
			List<DavResource> resources = SardineService.getInstance().list(media.getResource().getHref().toString(), 1);
			for (DavResource resource : resources) {
				if (!resource.isDirectory() && FilesUtils.isVideo(resource)){
					media = (Film)OMDbUtils.getOmdbFilm(FilenameUtils.removeExtension(resource.getName()), Film.class);
					if (media != null) {
						media.build(resource);
						System.out.println("Film " + resource.getName());
						DAOMedia.insertMedia(media);
					}else {
						MissingMedia missingMedia = new MissingMedia(resource.getName(), resource.getHref().toString(), "movie");
						DAOMedia.insertMissingMedia(missingMedia);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public static void listFilesWebdav(TypeMedia typeMedia, Media parentMedia) {
//		try {
//			List<DavResource> resources = SardineService.getInstance().list(parentMedia.getResource().getHref().toString(), 1);
//			Media media = null;
//			for (DavResource resource : resources) {
//				if (!isSameResource(resource, parentMedia.getResource())) {
//					if (resource.isDirectory()) { 
//						if (resource.getName().startsWith("Saison")) {
//							media = (Saison)OMDbUtils.getOmdbSaison(Saison.class, 
//									((Saison)parentMedia).getIdSerie(),  
//									FilesUtils.getNumberInText(resource.getName()));
//							System.out.println("Saison " + resource.getName());
//						}else {
//							media = (Serie)OMDbUtils.getOmdbMedia(resource.getName(), Serie.class, TypeMedia.serie);
//							media.build(resource);
//							System.out.println("Serie " + resource.getName());
//						}
//						int mediaId = DAOMedia.insertMedia(media);
//						media.setId(mediaId);
//						listFilesWebdav(typeMedia, media);
////						parentMedia.addMedia(media);
//					} else if (FilesUtils.isVideo(resource)){
//						if (typeMedia == TypeMedia.serie) {
//							media = (Episode)OMDbUtils.getOmdbEpisode(Episode.class, 
//									((Saison)parentMedia).getIdSerie(), 
//									((Saison)parentMedia).getNumSaison(), 
//									FilesUtils.getNumberInText(resource.getName()));
//							System.out.println("Episode " + resource.getName());
////							parentMedia.addMedia(media);
//						}else {
//							media = (Film)OMDbUtils.getOmdbMedia(FilenameUtils.removeExtension(resource.getName()), Film.class, TypeMedia.film);
//							media.build(resource);
//							System.out.println("Film " + resource.getName());
////							parentMedia.addMedia(media);
//						}
//						DAOMedia.insertMedia(media);
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static int getNumberOfFilesInDirectory(File directory) {
		File[] files = directory.listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isFile();
		    }
		});
		return files != null ? files.length : -1;
	}
	
	public static int getNumberOfFilesInDirectory(DavResource directory) {
		try {
			List<DavResource> resources = SardineService.getInstance().list(directory.getHref().toString());
			List<DavResource> fileResources = resources.stream()
					.filter(isFile())
					.collect(Collectors.toList());
			return fileResources != null ? fileResources.size() : -1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int getNumberOfSubDirectories(File directory) {
		File[] files = directory.listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isDirectory();
		    }
		});
		return files != null ? files.length : -1;
	}
	
	public static int getNumberInText(String text) {
		try {
			return Integer.parseInt(text.replaceAll("[^0-9]", ""));
		} catch(NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
		return -1;
	}
	
	public static boolean isVideo(File file) {
		try {
			Path path = file.toPath();
		    String mimeType = Files.probeContentType(path);
		    if (mimeType != null)
		    	return mimeType.contains("video");
		    System.out.println("File " + file.getPath() + " doesn't have a mimetype");
		    return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isVideo(DavResource resource) {
		return resource.getContentType().contains("video");
	}
	
	public static Predicate<DavResource> isFile() {
	    return resource -> !resource.isDirectory();
	}
}
