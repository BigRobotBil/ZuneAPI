package me.hyname.storage.impl;

import lombok.Getter;
import me.hyname.model.Album;
import me.hyname.model.Artist;
import me.hyname.model.Track;
import me.hyname.storage.Storage;

import java.util.List;

/**
 * @author Hyname
 * @apiNote Microsoft Azure Cosmos Implementation
 * <p>
 * @implNote I personally don't recommend using Cosmos
 * <p>Heres Why:
 * <p>- Closed Source
 * <p>- Unable to self-host
 * <p>- Unable to test this as I'm unable to self-host a cosmos db
 */
public class
CosmosStorage extends Storage {

    @Getter public String host;
    @Getter public String masterKey;

    // ill finish this later

    @Override
    public void init() {

    }

    @Override
    public boolean saveArtist(Artist artist) {
        return false;
    }

    @Override
    public Artist readArtist(String id) {
        return null;
    }

    @Override
    public boolean saveTrack(Track track) {
        return false;
    }

    @Override
    public Track readTrack(String id) {
        return null;
    }

    @Override
    public boolean saveAlbum(Album album) {
        return false;
    }

    @Override
    public Album readAlbum(String id) {
        return null;
    }

    @Override
    public List<Album> readAlbumsByArtist(Artist artist) {
        return null;
    }

    @Override
    public List<Track> readTracksByArtist(Artist artist) {
        return null;
    }

    @Override
    public List<Album> getAlbums() {
        return null;
    }

    @Override
    public List<Track> getTracks() {
        return null;
    }

    @Override
    public List<Artist> getArtists() {
        return null;
    }
}
