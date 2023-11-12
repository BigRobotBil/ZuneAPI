package me.hyname.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.route.track.GETTrackOverview;
import me.hyname.storage.Storage;

@RequestMapping
public class TrackController {

    GETTrackOverview getTrackOverview;

    public TrackController(Storage storage, JAXBContext jaxb) {
        getTrackOverview = new GETTrackOverview(storage, jaxb);
    }
    
    @RequestMapping(value = "/*/*/debug/trackoverview/", method = RequestMethod.GET)
    public String getTrackOverview(@PathVariable String id) {
        Map<ParamEnum, String> methodParams = new HashMap<>();
        methodParams.put(ParamEnum.ID, id);

        return getTrackOverview.handle(methodParams);
    }
}
