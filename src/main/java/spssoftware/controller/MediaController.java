package spssoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spssoftware.resource.AlbumResource;
import spssoftware.resource.MediaResourceAssember;
import spssoftware.resource.SearchResource;
import spssoftware.service.MediaService;

@RestController
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaResourceAssember mediaResourceAssember;

    @RequestMapping(value = "/media/albums", produces = "application/hal+json")
    public
    @ResponseBody
    SearchResource listAlbums() {
        return mediaResourceAssember.toSearchResource(mediaService.getAlbums());
    }

    @RequestMapping(value = "/media/albums/{albumName}", produces = "application/hal+json")
    public
    @ResponseBody
    AlbumResource getAlbum(@PathVariable("albumName") String albumName) {
        return mediaResourceAssember.toAlbumResource(mediaService.getAlbums().get(albumName));
    }

    @RequestMapping(value = "/media/albums/{albumName}/photos", produces = "application/hal+json")
    public
    @ResponseBody
    SearchResource listPhotos(@PathVariable("albumName") String albumName) {
        return mediaResourceAssember.toSearchResource(mediaService.getAlbums().get(albumName));
    }
}
