package me.hyname;

import lombok.Getter;
import me.hyname.model.*;
import me.hyname.route.chart.GETAlbumCharts;
import me.hyname.route.chart.GETPlaylistCharts;
import me.hyname.route.chart.GETTrackCharts;
import me.hyname.route.featured.GETFeaturedAlbums;
import me.hyname.route.featured.GETFeatures;
import me.hyname.route.genre.GETGenres;
import me.hyname.route.image.GETOtherImage;
import me.hyname.route.album.GETAlbumOverview;
import me.hyname.route.artist.*;
import me.hyname.route.image.GETBackgroundImage;
import me.hyname.route.image.GETPrimaryImageRoute;
import me.hyname.route.misc.GETHomeRoute;
import me.hyname.storage.Storage;
import me.hyname.storage.impl.MongoStorage;
import me.hyname.store.ArtistStorage;
import spark.Spark;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Zune API Checklist:
 * [o] Better Documentation
 * [/] Artist Routes
 * [o] Store Routes
 * [/] Album Routes
 * [/] Track Routes
 * [o] Similar Artists Route
 * [o] Streaming????
 * [/] Better image Routes
 *
 * x - done
 * o - to do
 * / - in progress
 */
public class Main {

    @Getter  private static Storage storage;

    public static void main(String[] args) throws Exception {

        (storage = new MongoStorage("127.0.0.1", 27017)).init();

        new ArtistStorage();

//        storage.saveTrack(new Track(
//                "Welcome To New York",
//                "Welcome To New York",
//                UUID.fromString("897e99e9-7881-44d3-8295-69e4047e9ba2"),
//                213,
//                1,
//                1,
//                new MiniAlbum("1989", UUID.fromString("ea1ecbf2-baaf-4a48-a2b4-13d3c79d0530")),
//                new MiniArtist("Taylor Swift", UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")),
//                100,
//                null,
//                0,
//                true,
//                true,
//                true,
//                true,
//                false,
//                true,
//                true,
//                false,
//                true));



        Spark.port(80);

        Spark.get("/*/image/:id", new GETOtherImage());

        // Artist Image Routes //
        Spark.get("/*/en-US/music/artist/:id/images/", new GETArtistImages()); // TODO: Redo
        Spark.get("/*/music/artist/:id/PrimaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/music/artist/:id/primaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/music/artist/:id/deviceBackgroundImage", new GETBackgroundImage()); // TODO: Redo
        Spark.get("/*/en-US/music/artist/:id/deviceBackgroundImage", new GETBackgroundImage()); // TODO: Redo


        // Artist Album Routes //
        Spark.get("/*/music/artist/:id/albums/", new GETArtistAlbums());
        Spark.get("/*/music/artist/:id/albums", new GETArtistAlbums());
        Spark.get("/*/music/artist/:id/appearsOnAlbums/", new GETArtistAlbums());

        // Artist Information Routes //
        Spark.get("/*/music/artist/:id/", new GETArtistOverview());
        Spark.get("/*/music/artist/:id", new GETArtistOverview());
        Spark.get("/*/en-US/music/artist/:id/", new GETArtistOverview());
        Spark.get("/*/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/*/en-US/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/*/en-US/music/artist/:id/biography", new GETArtistBiography()); // TODO: Redo


        // Album Related Routes //
        Spark.get("/*/en-US/music/album/:id/image", new GETPrimaryImageRoute());
        Spark.get("/*/music/album/:id/", new GETAlbumOverview());

        // Track Routes //
        Spark.get("/*/music/track/:id/", new GETTrackCharts());

        // Marketplace Routes //
        Spark.get("/*/music/chart/zune/albums/", new GETAlbumCharts());
        Spark.get("/*/music/chart/zune/tracks/", new GETTrackCharts()); // testing
        Spark.get("/*/music/chart/zune/playlists/", new GETPlaylistCharts()); // testing
        Spark.get("/*/music/genre/", new GETGenres());
        Spark.get("/*/music/featured/albums/", new GETFeaturedAlbums()); // testing
        Spark.get("/*/music/features/", new GETFeatures()); // testing
        //Spark.get("/v3.2/music/playlistFeatures/", new GETFeatures()); // testing
        Spark.get("/*/music/artist/:id/similarArtists/", new GETSimilarArtists());

        Spark.get("/", new GETHomeRoute());

        Spark.get("/*", (req, res) -> {
            System.out.println(req.url() + " | " + req.contextPath() + " | " + req.params() + " | " + req.queryParams() + " | " + req.queryString());
            return "";
        });

        System.out.println("Started server on port " + Spark.port());
    }
}