package me.hyname.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.route.album.GETAlbumOverview;
import me.hyname.storage.Storage;

@RestController
public class AlbumController {
    
    GETAlbumOverview getAlbumOverview;

    public AlbumController(Storage storage, JAXBContext jaxb) {
        getAlbumOverview = new GETAlbumOverview(storage, jaxb);
    }

    @RequestMapping(value = "/*/*/music/album/{id}/", method = RequestMethod.GET)
    public String getAlbumOverview(@PathVariable String id) {
        Map<ParamEnum, String> params = new HashMap<>();
        params.put(ParamEnum.ID, id);
        return getAlbumOverview.handle(params);
    }
}
