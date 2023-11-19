package me.hyname.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.route.artist.GETArtistAlbums;
import me.hyname.route.artist.GETArtistBiography;
import me.hyname.route.artist.GETArtistImages;
import me.hyname.route.artist.GETArtistOverview;
import me.hyname.route.artist.GETSimliarArtists;
import me.hyname.route.artist.GETArtistTracks;
import me.hyname.storage.Storage;

/**
 * Rest Controller for any/all Artist-related operations at the "artist" path
 */
@RestController
public class ArtistController {

    GETArtistBiography getArtistBiography;
    GETArtistAlbums getArtistAlbums;
    GETArtistImages getArtistImages;
    GETArtistOverview getArtistOverview;
    GETArtistTracks getArtistTracks;
    GETSimliarArtists getSimilarArtists;

    public ArtistController(Storage storage, JAXBContext jaxb) {
        getArtistAlbums = new GETArtistAlbums(storage, jaxb);
        getArtistBiography = new GETArtistBiography(storage, jaxb);
        getArtistImages = new GETArtistImages(storage, jaxb);
        getArtistOverview = new GETArtistOverview(storage, jaxb);
        getArtistTracks = new GETArtistTracks(storage, jaxb);
        getSimilarArtists = new GETSimliarArtists(storage, jaxb);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/albums/", "/*/*/music/artist/{id}/albums"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getArtistAlbums(@PathVariable String id, @RequestParam Map<String, String> params) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);
        methodParams.put(ParamEnum.ORDERBY, params.getOrDefault("orderBy", ""));

        //return getArtistAlbums.handle(methodParams);
        return getArtistAlbums.handle(methodParams);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/biography/", "/*/*/music/artist/{id}/biography"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getArtistBiography(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getArtistBiography.handle(methodParams);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/images/", "/*/*/music/artist/{id}/images"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getArtistImages(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getArtistImages.handle(methodParams);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/", "/*/*/music/artist/{id}"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getArtistOverview(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getArtistOverview.handle(methodParams);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/tracks/", "/*/*/music/artist/{id}/tracks"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getArtistTracks(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getArtistTracks.handle(methodParams);
    }

    @RequestMapping(value = {"/*/*/music/artist/{id}/similarArtists/", "/*/*/music/artist/{id}/similarArtists"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getSimilarArtists(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getSimilarArtists.handle(methodParams);
    }
}
