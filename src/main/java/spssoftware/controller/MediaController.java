package spssoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spssoftware.resource.*;
import spssoftware.service.MediaService;

@RestController
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaResourceAssember mediaResourceAssember;

    @RequestMapping(value = "/media/folders", produces = "application/hal+json")
    public @ResponseBody SearchResource listFolders() {
        return mediaResourceAssember.toSearchResource(mediaService.getContent());
    }

    @RequestMapping(value = "/media/folders/{folderName}", produces = "application/hal+json")
    public @ResponseBody FolderResource getFolder(@PathVariable("folderName") String folderName) {
        return mediaResourceAssember.toFolderResource(folderName);
    }

    @RequestMapping(value = "/media/folders/{folderName}/albums", produces = "application/hal+json")
    public @ResponseBody SearchResource listAlbums(@PathVariable("folderName") String folderName) {
        return mediaResourceAssember.toSearchResource(folderName, mediaService.getContent().get(folderName));
    }

    @RequestMapping(value = "/media/folders/{folderName}/albums/{albumName}", produces = "application/hal+json")
    public @ResponseBody AlbumResource getAlbum(@PathVariable("folderName") String folderName, @PathVariable("albumName") String albumName) {
        return mediaResourceAssember.toAlbumResource(folderName, mediaService.getContent().get(folderName).get(albumName));
    }

    @RequestMapping(value = "/media/folders/{folderName}/albums/{albumName}/photos", produces = "application/hal+json")
    public @ResponseBody SearchResource listPhotos(@PathVariable("folderName") String folderName, @PathVariable("albumName") String albumName) {
        return mediaResourceAssember.toSearchResource(folderName, albumName, mediaService.getContent().get(folderName).get(albumName));
    }
}
