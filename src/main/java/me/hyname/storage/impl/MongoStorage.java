package me.hyname.storage.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.Getter;
import me.hyname.model.*;
import me.hyname.storage.Storage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class MongoStorage extends Storage {

    @Getter private String host;
    @Getter private int port;
    private MongoClient client;
    private MongoDatabase database;


    private MongoCollection<Document> albumCollection;
    private MongoCollection<Document> artistCollection;
    private MongoCollection<Document> trackCollection;

    public MongoStorage(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void init() {
        this.client = new MongoClient(host, port);
        this.database = client.getDatabase("zune");
        this.albumCollection = database.getCollection("albums");
        this.artistCollection = database.getCollection("artists");
        this.trackCollection = database.getCollection("tracks");
    }

    @Override
    public boolean saveArtist(Artist artist) {
        try {
            Document doc = artist.toMongo();
            Document searchedDocument = artistCollection.find(Filters.eq("id", doc.getString("id"))).first();
            boolean exists = searchedDocument != null;

            if(!exists)
                artistCollection.insertOne(doc);
            else
                artistCollection.replaceOne(searchedDocument, doc);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Artist readArtist(String id) {
        Document readDocument = artistCollection.find(Filters.eq("id", id)).first();

        if (readDocument == null) throw new NullPointerException("Requested document \"" + id + "\" does not exist!");

        List<Genre> genres = new ArrayList<>();
        for (Document genreDoc : readDocument.getList("genres", Document.class))
            genres.add(Genre.fromMongo(genreDoc));

        List<Mood> moods = new ArrayList<>();
        for (Document moodDoc : readDocument.getList("moods", Document.class))
            moods.add(Mood.fromMongo(moodDoc));

        List<MiniImage> images = new ArrayList<>();
        for (Document imageDoc : readDocument.getList("images", Document.class))
            images.add(MiniImage.fromMongo(imageDoc));


        Artist returnValue =
                new Artist(
                        readDocument.getString("sortTitle"),
                        readDocument.getString("title"),
                        UUID.fromString(readDocument.getString("id")),
                        UUID.fromString(readDocument.getString("imageId")),
                        readDocument.getDouble("popularity"),
                        readDocument.getBoolean("isVariousArtist"),
                        readDocument.getString("biographyLink"),
                   //     readDocument.getString("biography"),
                        readDocument.getString("shortBiography"),
                        readDocument.getInteger("playCount"),
                        Genre.fromMongo(readDocument.get("primaryGenre", Document.class)),
                        genres,
                        moods,
                        MiniImage.fromMongo(readDocument.get("backgroundImage", Document.class)),
                        readDocument.getBoolean("hasRadioChannel"),
                        images
                );

        return returnValue;

    }

    @Override
    public boolean saveTrack(Track track) {
        try {
            Document doc = track.toMongo();
            Document searchedDocument = trackCollection.find(Filters.eq("id", doc.getString("id"))).first();
            boolean exists = searchedDocument != null;

            if(!exists)
                trackCollection.insertOne(doc);
            else
                trackCollection.replaceOne(searchedDocument, doc);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Track readTrack(String id) {
        UUID uuid = UUID.fromString(id);
        Document readDocument = trackCollection.find(Filters.eq("id", uuid.toString())).first();

        if (readDocument == null) throw new NullPointerException("Requested document \"" + id + "\" does not exist!");

        Track returnValue =
                new Track(
                        readDocument.getString("sortTitle"),
                        readDocument.getString("title"),
                        UUID.fromString(readDocument.getString("id")),
                        readDocument.getInteger("lengthSeconds"),
                        readDocument.getInteger("trackNumber"),
                        readDocument.getInteger("discNumber"),
                        MiniAlbum.fromMongo(readDocument.get("album", Document.class)),
                        MiniArtist.fromMongo(readDocument.get("albumArtist", Document.class)),
                        readDocument.getInteger("playCount"),
                        readDocument.get("musicVideoId", UUID.class),
                        readDocument.getInteger("pointsPrice"),
                        readDocument.getBoolean("canPlay"),
                        readDocument.getBoolean("canDownload"),
                        readDocument.getBoolean("canPurchase"),
                        readDocument.getBoolean("canPurchaseMP3"),
                        readDocument.getBoolean("canPurchaseAlbumOnly"),
                        readDocument.getBoolean("canSync"),
                        readDocument.getBoolean("canBurn"),
                        readDocument.getBoolean("inCollection"),
                        readDocument.getBoolean("isDownloading")
                );


        return returnValue;

    }

    @Override
    public boolean saveAlbum(Album album) {
        try {
            Document doc = album.toMongo();
            Document searchedDocument = albumCollection.find(Filters.eq("id", doc.getString("id"))).first();
            boolean exists = searchedDocument != null;

            if(!exists)
                albumCollection.insertOne(doc);
            else
                albumCollection.replaceOne(searchedDocument, doc);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Album readAlbum(String id) {
        Document readDocument = albumCollection.find(Filters.eq("id", id)).first();

        if (readDocument == null) throw new NullPointerException("Requested document \"" + id + "\" does not exist!");

        List<MiniImage> images = new ArrayList<>();

        for (Document d : readDocument.getList("images", Document.class)) {
            images.add(new MiniImage(UUID.fromString(d.getString("id"))));
        }

        List<MiniTrack> tracks = new ArrayList<>();
        for (Document d : readDocument.getList("tracks", Document.class)) {
            tracks.add(MiniTrack.fromMongo(d));
        }

        Album returnValue =
                new Album(
                        readDocument.getString("title"),
                        readDocument.getString("sortTitle"),
                        UUID.fromString(readDocument.getString("id")),
                        MiniArtist.fromMongo(readDocument.get("primaryArtist", Document.class)),
                        Genre.fromMongo(readDocument.get("primaryGenre", Document.class)),
                        readDocument.getInteger("popularity", 0),
                        readDocument.getBoolean("explicit"),
                        readDocument.getBoolean("premium"),
                        images,
                        readDocument.getDate("releaseDate"),
                        tracks
                );


        return returnValue;
    }

    @Override
    public List<Album> readAlbumsByArtist(Artist artist) {
        List<Album> finalOutput = new ArrayList<>();
        FindIterable<Document> documents = albumCollection.find(Filters.eq("primaryArtist", new MiniArtist(artist.getName(), artist.getDbId()).toMongo()));

        documents.forEach((Consumer<? super Document>)  document -> {
            finalOutput.add(readAlbum(document.getString("id")));
        });

        return finalOutput;
    }

    @Override
    public List<Track> readTracksByArtist(Artist artist) {
        List<Track> finalOutput = new ArrayList<>();
        FindIterable<Document> documents = trackCollection.find(Filters.eq("primaryArtist", new MiniArtist(artist.getName(), artist.getDbId()).toMongo()));

        documents.forEach((Consumer<? super Document>)  document -> {
            finalOutput.add(readTrack(document.getString("id")));
        });

        return finalOutput;
    }

    @Override
    public List<Album> getAlbums() {
        List<Album> finalOutput = new ArrayList<>();
        FindIterable<Document> documents = albumCollection.find();

        documents.forEach((Consumer<? super Document>)  document -> {
            finalOutput.add(readAlbum(document.getString("id")));
        });

        return finalOutput;
    }

    @Override
    public List<Track> getTracks() {
        List<Track> finalOutput = new ArrayList<>();
        FindIterable<Document> documents = trackCollection.find();

        documents.forEach((Consumer<? super Document>)  document -> {
            finalOutput.add(readTrack(document.getString("id")));
        });

        return finalOutput;
    }
}
