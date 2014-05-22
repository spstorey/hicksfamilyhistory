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

    public SearchResource toSearchResource(Map<String, Album> albums) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<>();
        for (Map.Entry<String, Album> albumEntry : albums.entrySet()) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(albumEntry.getValue().getName());
            summaries.add(summaryResource);
            summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getAlbum(albumEntry.getValue().getName())).withSelfRel());
        }

        searchResource.get_embedded().put("albums", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listAlbums()).withSelfRel());
        return searchResource;
    }

    public AlbumResource toAlbumResource(Album album) {
        AlbumResource albumResource = new AlbumResource();
        albumResource.setName(album.getName());
        albumResource.setDisplayDate(album.getDisplayDate());
        albumResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getAlbum(album.getName())).withSelfRel());
        if (album.getCoverPhoto() != null) {
            albumResource.add(new Link(album.getCoverPhoto().getUrl()).withRel("coverPhoto"));
        }
        albumResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listPhotos(album.getName())).withRel("photos"));

        return albumResource;
    }

    public SearchResource toSearchResource(Album album) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<>();
        for (Photo photo : album.getPhotos()) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(photo.getCaption());
            summaries.add(summaryResource);
            summaryResource.add(new Link(photo.getUrl(), photo.getFilename()));
            summaryResource.add(new Link(photo.getThumbnailUrl(), "thumbnail"));
        }

        searchResource.get_embedded().put("photos", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listPhotos(album.getName())).withSelfRel());
        return searchResource;
    }
}
