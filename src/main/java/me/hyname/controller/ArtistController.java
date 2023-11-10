package me.hyname.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.route.artist.GETArtistAlbums;
import me.hyname.route.artist.GETArtistTracks;
import me.hyname.storage.Storage;

/**
 * Rest Controller for any/all Artist-related operations at the "artist" path
 */
@RestController
public class ArtistController {

    GETArtistTracks getArtistTracks;
    GETArtistAlbums getArtistAlbums;


    public ArtistController(Storage storage, JAXBContext jaxb) {
        getArtistTracks = new GETArtistTracks(storage, jaxb);
        getArtistAlbums = new GETArtistAlbums(storage, jaxb);
    }

    @RequestMapping(value = "/*/*/music/artist/{id}/tracks/", method = RequestMethod.GET)
    public String getArtistTracks(@PathVariable String id) {
        return getArtistTracks.handle(id);
    }
    
    @RequestMapping(value = "/*/*/music/artist/{id}/albums/", method = RequestMethod.GET)
    public String getArtistAlbums(@PathVariable String id, @RequestParam Map<String, String> params) {
        return getArtistAlbums.handle(id, params.getOrDefault("orderBy", ""));
    }
}
