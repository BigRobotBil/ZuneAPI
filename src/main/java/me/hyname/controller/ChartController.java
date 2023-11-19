package me.hyname.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBContext;
import me.hyname.route.chart.GETAlbumCharts;
import me.hyname.route.chart.GETPlaylistCharts;
import me.hyname.route.chart.GETTrackCharts;
import me.hyname.storage.Storage;

@RestController
public class ChartController {
    GETAlbumCharts getAlbumCharts;
    GETPlaylistCharts getPlaylistCharts;
    GETTrackCharts getTrackCharts;

    public ChartController(Storage storage, JAXBContext jaxb) {
        getAlbumCharts = new GETAlbumCharts(storage, jaxb);
        getPlaylistCharts = new GETPlaylistCharts(storage, jaxb);
        getTrackCharts = new GETTrackCharts(storage, jaxb);
    }

    @RequestMapping(value = {"/*/*/music/chart/zune/albums/", "/*/*/music/chart/zune/albums"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getAlbumCharts(@PathVariable String id, @RequestParam Map<String, String> params) {
        return getAlbumCharts.handle(new HashMap<>());
    }

    @RequestMapping(value = {"/*/*/music/chart/zune/playlists/", "/*/*/music/chart/zune/playlists"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getPlaylistCharts(@PathVariable String id, @RequestParam Map<String, String> params) {
        return getPlaylistCharts.handle(new HashMap<>());
    }

    @RequestMapping(value = {"/*/*/music/chart/zune/tracks/", "/*/*/music/chart/zune/tracks"}, method = RequestMethod.GET, produces = "text/xml")
    public byte[] getTrackCharts(@PathVariable String id, @RequestParam Map<String, String> params) {
        return getTrackCharts.handle(new HashMap<>());
    }
}
