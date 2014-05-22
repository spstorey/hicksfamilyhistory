package spssoftware.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import spssoftware.controller.MediaController;
import spssoftware.domain.Album;
import spssoftware.domain.Photo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MediaResourceAssember {

    public SearchResource toSearchResource(Map<String, Map<String, Album>> content) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<SummaryResource>();
        for (String folderName : content.keySet()) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(folderName);
            summaries.add(summaryResource);
            summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getFolder(folderName)).withSelfRel());
        }

        searchResource.get_embedded().put("folders", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listFolders()).withSelfRel());
        return searchResource;
    }

    public FolderResource toFolderResource(String name) {
        FolderResource folderResource = new FolderResource();
        folderResource.setName(name);
        folderResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getFolder(name)).withSelfRel());
        folderResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listAlbums(name)).withRel("albums"));
        return folderResource;
    }

    public SearchResource toSearchResource(String folderName, Map<String, Album> albums) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<SummaryResource>();
        for (Map.Entry<String, Album> folderEntry : albums.entrySet()) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(folderEntry.getValue().getName());
            summaries.add(summaryResource);
            summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getAlbum(folderName, folderEntry.getValue().getName())).withSelfRel());
        }

        searchResource.get_embedded().put("albums", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listAlbums(folderName)).withSelfRel());
        return searchResource;
    }

    public AlbumResource toAlbumResource(String folderName, Album album) {
        AlbumResource albumResource = new AlbumResource();
        albumResource.setName(album.getName());
        albumResource.setDisplayDate(album.getDisplayDate());
        albumResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getAlbum(folderName, album.getName())).withSelfRel());
        if (album.getCoverPhoto() != null) {
            albumResource.add(new Link(album.getCoverPhoto().getUrl()).withRel("coverPhoto"));
        }
        albumResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listPhotos(folderName, album.getName())).withRel("photos"));

        return albumResource;
    }

    public SearchResource toSearchResource(String folderName, String albumName, Album album) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<SummaryResource>();
        for (Photo photo : album.getPhotos()) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(photo.getCaption());
            summaries.add(summaryResource);
            summaryResource.add(new Link(photo.getUrl(), photo.getFilename()));
            summaryResource.add(new Link(photo.getThumbnailUrl(), "thumbnail"));
        }

        searchResource.get_embedded().put("photos", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listPhotos(folderName, album.getName())).withSelfRel());
        return searchResource;
    }
}
