package me.hyname;

import lombok.Getter;
import me.hyname.handler.ZuneErrorHandler;
import me.hyname.route.album.GETRelatedAlbums;
import me.hyname.route.album.GETRelatedArtistAlbums;
import me.hyname.route.chart.GETAlbumCharts;
import me.hyname.route.chart.GETPlaylistCharts;
import me.hyname.route.chart.GETTrackCharts;
import me.hyname.route.featured.GETNewlyReleasedAlbums;
import me.hyname.route.featured.GETFeaturedCards;
import me.hyname.route.genre.GETGenres;
import me.hyname.route.image.GETOtherImage;
import me.hyname.route.album.GETAlbumOverview;
import me.hyname.route.artist.*;
import me.hyname.route.image.GETDeviceBackgroundImage;
import me.hyname.route.image.GETPrimaryImageRoute;
import me.hyname.route.misc.GETHomeRoute;
import me.hyname.route.mix.GETSimilarTracks;
import me.hyname.route.tuners.GETPCConfiguration;
import me.hyname.storage.Storage;
import me.hyname.storage.impl.MongoStorage;
import me.hyname.store.ArtistStorage;
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

        new ArtistStorage();

        Spark.port(80);

        Spark.exception(Exception.class, new ZuneErrorHandler());

//        Spark.internalServerError();
//        Spark.notFound();

        registerRoutes();
        registerLegacyRoutes();

        Spark.get("/*", (req, res) -> {
            System.out.println(req.url() + " | " + req.contextPath() + " | " + req.params() + " | " + req.queryParams() + " | " + req.queryString());
            return "";
        });

        System.out.println("Started server on port " + Spark.port());
    }

    private static void registerRoutes() {



        Spark.get("/*/*/image/:id", new GETOtherImage());

        // Artist Image Routes //
        Spark.get("/*/*/music/artist/:id/images/", new GETArtistImages()); // TODO: Redo
        Spark.get("/*/*/music/artist/:id/images", new GETArtistImages()); // TODO: Redo
        Spark.get("/*/*/music/artist/:id/PrimaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/*/music/artist/:id/primaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/*/music/artist/:id/deviceBackgroundImage", new GETDeviceBackgroundImage()); // TODO: Redo
        Spark.get("/*/*/en-US/music/artist/:id/deviceBackgroundImage", new GETDeviceBackgroundImage()); // TODO: Redo


        // Artist Album Routes //
        Spark.get("/*/*/music/artist/:id/albums/", new GETArtistAlbums());
        Spark.get("/*/*/music/artist/:id/albums", new GETArtistAlbums());
        Spark.get("/*/*/music/artist/:id/appearsOnAlbums/", new GETArtistAlbums());

        Spark.get("/*/*/music/artist/:id/tracks/", new GETArtistTracks());
        Spark.get("/*/*/track/:id/similarTracks", new GETSimilarTracks());
        Spark.get("/*/*/music/album/:id/relatedAlbums/", new GETRelatedAlbums());
        Spark.get("/*/*/music/artist/:id/relatedAlbums/", new GETRelatedArtistAlbums());

        // Artist Information Routes //
        Spark.get("/*/*/music/artist/:id/", new GETArtistOverview());
        Spark.get("/*/*/music/artist/:id", new GETArtistOverview());
        Spark.get("/*/*/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/*/*/music/artist/:id/biography", new GETArtistBiography()); // TODO: Redo


        // Album Related Routes //
        Spark.get("/*/*/en-US/music/album/:id/image", new GETPrimaryImageRoute());
        Spark.get("/*/*/music/album/:id/", new GETAlbumOverview());

        // Track Routes //
        Spark.get("/*/*/music/track/:id/", new GETTrackCharts());

        // Marketplace Routes //
        Spark.get("/*/*/music/chart/zune/albums/", new GETAlbumCharts());
        Spark.get("/*/*/music/chart/zune/tracks/", new GETTrackCharts()); // testing
        Spark.get("/*/*/music/chart/zune/playlists/", new GETPlaylistCharts()); // testing
        Spark.get("/*/*/music/genre/", new GETGenres());
        Spark.get("/*/*/music/featured/albums/", new GETNewlyReleasedAlbums()); // testing
        Spark.get("/*/*/music/features/", new GETFeaturedCards()); // testing
        //Spark.get("/v3.2/music/playlistFeatures/", new GETFeatures()); // testing
        Spark.get("/*/*/music/artist/:id/similarArtists/", new GETSimilarArtists());
        Spark.get("/*/*/music/artist/:id/similarArtists", new GETSimilarArtists());

        Spark.get("/en-US/ZunePCClient/*/configuration.xml", new GETPCConfiguration());
        Spark.get("/en-US/zunepcclient/*/configuration.xml", new GETPCConfiguration());
        Spark.get("/ZunePCClient/*/configuration.xml", new GETPCConfiguration());
        Spark.get("/", new GETHomeRoute());

    }

    private static void registerLegacyRoutes() {
        Spark.get("/*/image/:id", new GETOtherImage());
        Spark.get("/*/en-US/music/artist/:id/images/", new GETArtistImages());
        Spark.get("/*/music/artist/:id/PrimaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/music/artist/:id/primaryImage", new GETPrimaryImageRoute());
        Spark.get("/*/music/artist/:id/deviceBackgroundImage", new GETDeviceBackgroundImage());
        Spark.get("/*/en-US/music/artist/:id/deviceBackgroundImage", new GETDeviceBackgroundImage());
        Spark.get("/*/music/artist/:id/albums/", new GETArtistAlbums());
        Spark.get("/*/music/artist/:id/albums", new GETArtistAlbums());
        Spark.get("/*/music/artist/:id/appearsOnAlbums/", new GETArtistAlbums());
        Spark.get("/*/music/artist/:id/", new GETArtistOverview());
        Spark.get("/*/music/artist/:id", new GETArtistOverview());
        Spark.get("/*/en-US/music/artist/:id/", new GETArtistOverview());
        Spark.get("/*/music/artist/:id/biography/", new GETArtistBiography());
        Spark.get("/*/en-US/music/artist/:id/biography/", new GETArtistBiography());
        Spark.get("/*/en-US/music/artist/:id/biography", new GETArtistBiography());
        Spark.get("/*/en-US/music/album/:id/image", new GETPrimaryImageRoute());
        Spark.get("/*/music/album/:id/", new GETAlbumOverview());
        Spark.get("/*/music/track/:id/", new GETTrackCharts());
        Spark.get("/*/music/chart/zune/albums/", new GETAlbumCharts());
        Spark.get("/*/music/chart/zune/tracks/", new GETTrackCharts()); // testing
        Spark.get("/*/music/chart/zune/playlists/", new GETPlaylistCharts()); // testing
        Spark.get("/*/music/genre/", new GETGenres());
        Spark.get("/*/music/featured/albums/", new GETNewlyReleasedAlbums()); // testing
        Spark.get("/*/music/features/", new GETFeaturedCards()); // testing
        Spark.get("/*/music/artist/:id/similarArtists/", new GETSimilarArtists());
        Spark.get("/*/music/artist/:id/similarArtists", new GETSimilarArtists());
    }
}