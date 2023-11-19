package me.hyname.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.route.tuners.GETPCConfiguration;
import me.hyname.storage.Storage;

@RestController
public class TunersController {

    GETPCConfiguration getpcConfiguration;

    public TunersController(Storage storage, JAXBContext jaxb) {
        getpcConfiguration = new GETPCConfiguration(storage, jaxb);
    }
    
    @RequestMapping(value = {"/en-US/ZunePCClient/*/configuration.xml"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getpcConfiguration() {
        return getpcConfiguration.handle(new HashMap<>());
    }
}