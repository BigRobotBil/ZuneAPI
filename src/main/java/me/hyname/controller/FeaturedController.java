package me.hyname.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.route.featured.GETFeaturedCards;
import me.hyname.route.featured.GETNewlyReleasedAlbums;
import me.hyname.storage.Storage;

@RestController
public class FeaturedController {
    GETFeaturedCards getFeaturedCards;
    GETNewlyReleasedAlbums getNewlyReleasedAlbums;

    public FeaturedController (Storage storage, JAXBContext jaxb) {
        getFeaturedCards = new GETFeaturedCards(storage, jaxb);
        getNewlyReleasedAlbums = new GETNewlyReleasedAlbums(storage, jaxb);
    }

    @RequestMapping(value = {"/*/*/music/features/", "/*/*/music/features"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getFeaturedCards() {
        return getFeaturedCards.handle(new HashMap<>());
    }

    @RequestMapping(value = {"/*/*/music/featured/albums/", "/*/*/music/featured/albums"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getNewlyReleasedAlbums() {
        return getNewlyReleasedAlbums.handle(new HashMap<>());
    }
}
