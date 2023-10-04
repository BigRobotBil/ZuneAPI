package me.hyname;

import lombok.Getter;
import me.hyname.album.GETAlbumOverview;
import me.hyname.artist.*;
import me.hyname.image.GETArtistThumbnailImage;
import me.hyname.image.GETBackgroundImage;
import me.hyname.image.GETPrimaryImageRoute;
import me.hyname.model.*;
import me.hyname.storage.Storage;
import me.hyname.storage.impl.MongoStorage;
import me.hyname.store.ArtistStorage;
import spark.Spark;
import spark.utils.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Main {

    @Getter  private static Storage storage;

    public static void main(String[] args) throws Exception {

        (storage = new MongoStorage("127.0.0.1", 27017)).init();

//        new ArtistStorage();
//
//        storage.saveAlbum(ArtistStorage.ts1989);
//        storage.saveArtist(ArtistStorage.taylorSwift);
//        storage.readArtist(("10450000-0200-11db-89ca-0019b92a3933"));
//        storage.readAlbum("ea1ecbf2-baaf-4a48-a2b4-13d3c79d0530");
//
//
        Artist artist = Main.getStorage().readArtist("10450000-0200-11db-89ca-0019b92a3933");
        Feed<Album> que=  new Feed<>();

        que.setEntries(Main.getStorage().readAlbumsByArtist(artist));
//        System.out.println(ArtistStorage.taylorSwift.toMongo().toString());
        Spark.port(80);

        Spark.get("/v3.2/music/artist/:id/PrimaryImage", new GETPrimaryImageRoute()); // TODO: Redo
        Spark.get("/v3.2/music/artist/:id/primaryImage", new GETPrimaryImageRoute()); // TODO: Redo
        Spark.get("/v3.2/music/artist/:id/deviceBackgroundImage", new GETBackgroundImage()); // TODO: Redo
        Spark.get("/v3.2/en-US/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/v3.2/en-US/music/artist/:id/images/", new GETArtistImages()); // TODO: Redo
        Spark.get("/v3.2/music/artist/:id/biography/", new GETArtistBiography()); // TODO: Redo
        Spark.get("/v3.2/music/artist/:id/albums/", new GETArtistAlbums());
        Spark.get("/v3.2/music/artist/:id/albums", new GETArtistAlbums());
        Spark.get("/v3.2/music/album/:id/", new GETAlbumOverview());
        Spark.get("/v3.2/music/artist/:id/appearsOnAlbums/", new GETArtistAlbums());

//        Spark.get("/v3.2/music/artist/10450000-0200-11db-89ca-0019b92a3933/", new GETArtistOverview2());
        Spark.get("/v3.2/en-US/music/artist/:id/", new GETArtistOverview2());
        Spark.get("/v3.2/en-US/music/album/:id/image", new GETPrimaryImageRoute());
        Spark.get("/v3.2/music/artist/:id/", new GETArtistOverview2());
        Spark.get("/v3.2/music/artist/:id", new GETArtistOverview2());
        Spark.get("/v3.2/image/:id", new GETArtistThumbnailImage());

        Spark.get("/*", (req, res) -> {
            System.out.println(req.url() + " | " + req.contextPath() + " | " + req.params() + " | " + req.queryParams() + " | " + req.queryString());
            return "";
        });
//        JAXBContext contextObj = JAXBContext.newInstance(Artist.class);
//
//        Marshaller marshallerObj = contextObj.createMarshaller();
//        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Artist que= ArtistStorage.taylorSwift;
//        marshallerObj.marshal(que, baos);
//
//        System.out.println(baos.toString(Charset.defaultCharset()));

        System.out.println("Hello world!");
    }

    //        que.setEntries(Arrays.asList(
//                new Album(
//                        "1989",
//                        "1989",
//                        UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"),
//                        new MiniArtist("Taylor Swift",
//                                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")
//                        ),
//                        Genre.POP,
//                        10,
//                        false,
//                        false,
//                        Arrays.asList(
//                                new MiniImage(UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"))
//                        ),
//                        new Date(1414373526000l),
//                        Arrays.asList(
//                                new Track(
//                                        "Welcome to New York", 60, 1, 1, new MiniAlbum("1989", UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5")), new MiniArtist("Taylor Swift", UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")), 100, null, 10, true, true, true, true, true, true, true, false, false)
//                        )
//                )
//        ));
}