package me.hyname;

import lombok.Getter;
import me.hyname.route.image.GETOtherImage;
import me.hyname.route.album.GETAlbumOverview;
import me.hyname.route.artist.*;
import me.hyname.route.image.GETBackgroundImage;
import me.hyname.route.image.GETPrimaryImageRoute;
import me.hyname.storage.Storage;
import me.hyname.storage.impl.MongoStorage;
import spark.Spark;

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
        Spark.port(80);

        Spark.get("/v3.2/image/:id", new GETOtherImage());

        // Artist Image Routes //
        Spark.get("/v3.2/en-US/music/artist/:id/images/", new GETArtistImages()); // TODO: Redo
        Spark.get("/v3.2/music/artist/:id/PrimaryImage", new GETPrimaryImageRoute());
        Spark.get("/v3.2/music/artist/:id/primaryImage", new GETPrimaryImageRoute());
        Spark.get("/v3.2/music/artist/:id/deviceBackgroundImage", new GETBackgroundImage()); // TODO: Redo


        // Artist Album Routes //
        Spark.get("/v3.2/music/artist/:id/albums/", new GETArtistAlbums());
        Spark.get("/v3.2/music/artist/:id/albums", new GETArtistAlbums());
        Spark.get("/v3.2/music/artist/:id/appearsOnAlbums/", new GETArtistAlbums());

        // Artist Information Routes //
        Spark.get("/v3.2/music/artist/:id/", new GETArtistOverview());
        Spark.get("/v3.2/music/artist/:id", new GETArtistOverview());
        Spark.get("/v3.2/en-US/music/artist/:id/", new GETArtistOverview());
        Spark.get("/v3.2/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/v3.2/en-US/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo


        // Album Related Routes //
        Spark.get("/v3.2/en-US/music/album/:id/image", new GETPrimaryImageRoute());
        Spark.get("/v3.2/music/album/:id/", new GETAlbumOverview());

        // Track Routes //
        Spark.get("/v3.2/music/album/:id/", new GETAlbumOverview());

        Spark.get("/*", (req, res) -> {
            System.out.println(req.url() + " | " + req.contextPath() + " | " + req.params() + " | " + req.queryParams() + " | " + req.queryString());
            return "";
        });

        System.out.println("Started server on port " + Spark.port());
    }
}