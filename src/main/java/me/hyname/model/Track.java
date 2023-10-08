package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "entry")
public class Track {

    @XmlElement(name = "title")
    public String sortTitle;

    @XmlElement(name = "sortTitle")
    public String title;

    @XmlElement(name = "id")
    public UUID id;

    @XmlElement(name = "length")
    public int lengthSeconds;

    @XmlElement(name = "trackNumber")
    public int trackNumber;

    @XmlElement(name = "discNumber")
    public int discNumber;

    @XmlElement(name = "album")
    public MiniAlbum album;

    @XmlElement(name = "albumArtist")
    public MiniArtist albumArtist;

    @XmlElement(name = "playCount")
    public int playCount;

    @XmlElement(name = "musicVideoId")
    public UUID musicVideoId;

    @XmlElement(name = "PointsPrice")
    public int pointsPrice;

    @XmlElement(name = "CanPlay")
    public boolean canPlay;

    @XmlElement(name = "CanDownload")
    public boolean canDownload;

    @XmlElement(name = "CanPurchase")
    public boolean canPurchase;

    @XmlElement(name = "CanPurchaseMP3")
    public boolean canPurchaseMP3;

    @XmlElement(name = "CanPurchaseAlbumOnly")
    public boolean canPurchaseAlbumOnly;

    @XmlElement(name = "CanSync")
    public boolean canSync;

    @XmlElement(name = "CanBurn")
    public boolean canBurn;

    @XmlElement(name = "InCollection")
    public boolean inCollection;

    @XmlElement(name = "IsDownloading")
    public boolean isDownloading;

    public Document toMongo() {
        Document toDb = new Document();
        toDb.put("sortTitle", this.sortTitle);
        toDb.put("id", this.id.toString());
        toDb.put("lengthSeconds", this.lengthSeconds);
        toDb.put("trackNumber", this.trackNumber);
        toDb.put("discNumber", this.discNumber);
        toDb.put("album", this.album.toMongo());
        toDb.put("albumArtist", this.albumArtist.toMongo());
        toDb.put("playCount", this.playCount);
        toDb.put("musicVideoId", this.musicVideoId);
        toDb.put("pointsPrice", this.pointsPrice);
        toDb.put("canPlay", canPlay);
        toDb.put("canDownload", canDownload);
        toDb.put("canPurchase", canPurchase);
        toDb.put("canPurchaseMP3", canPurchaseMP3);
        toDb.put("canPurchaseAlbumOnly", canPurchaseAlbumOnly);
        toDb.put("canSync", canSync);
        toDb.put("canBurn", canBurn);
        toDb.put("inCollection", inCollection);
        toDb.put("isDownloading", isDownloading);
        return toDb;
    }

}
