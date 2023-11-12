package me.hyname.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.xml.bind.JAXBContext;
import me.hyname.route.misc.GETHomeRoute;
import me.hyname.storage.Storage;

@RequestMapping
public class MiscController {
    GETHomeRoute getHomeRoute;

    public MiscController(Storage storage, JAXBContext jaxb) {
        getHomeRoute = new GETHomeRoute(storage, jaxb);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFeaturedCards() {
        return getHomeRoute.handle(new HashMap<>());
    }
}
