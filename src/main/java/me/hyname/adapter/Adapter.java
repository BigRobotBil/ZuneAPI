package me.hyname.adapter;

import me.hyname.model.Album;
import me.hyname.model.Artist;
import me.hyname.model.Track;

public abstract class Adapter {

    public abstract void init();
    public abstract Artist convertArtist(String name);

    public abstract Album convertAlbum(String name, String artistName);

    public abstract Track convertTrack(String name, String artistName, String albumName);
}
