package me.hyname.storage;

import me.hyname.model.Album;
import me.hyname.model.Artist;
import me.hyname.model.Track;

import java.util.List;
import java.util.UUID;


/**
 * Storage Class
 * @author hyname
 */
public abstract class Storage {

    /**
     * Initialization method for storage impl
     * (ie: connecting to MongoDB)
     */
    public abstract void init();

    public abstract boolean saveArtist(Artist artist);
    public abstract Artist readArtist(String id);


    public abstract boolean saveTrack(Track track);
    public abstract Track readTrack(String id);


    public abstract boolean saveAlbum(Album album);
    public abstract Album readAlbum(String id);

    public abstract List<Album> readAlbumsByArtist(Artist artist);

    public abstract List<Track> readTracksByArtist(Artist artist);
}
