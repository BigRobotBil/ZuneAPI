package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "feed")
public class Album {

    @XmlElement(name = "title")
    public String title;

    @XmlElement(name = "sortTitle")
    public String sortTitle;

    @XmlElement(name = "id")
    public UUID id;

    @XmlElement(name = "primaryArtist")
    public MiniArtist primaryArtist;

    @XmlElement(name = "primaryGenre")
    public Genre primaryGenre;

    @XmlElement(name = "playRank")
    public int popularity;

    @XmlElement(name = "isExplicit")
    public boolean explicit;


    @XmlElement(name = "isPremium")
    public boolean premium;

    @XmlElement(name = "image")
    public List<MiniImage> images;

    @XmlElement(name = "releaseDate")
    public Date releaseDate;

    @XmlElement(name = "entry")
    public List<MiniTrack> tracks;


    public Document toMongo() {
        Document toDb = new Document();
        toDb.put("sortTitle", this.sortTitle);
        toDb.put("title", this.title);
        toDb.put("id", this.id.toString());
        toDb.put("primaryArtist", this.primaryArtist.toMongo());
        toDb.put("primaryGenre", this.primaryGenre.toMongo());
        toDb.put("popularity", this.popularity);
        toDb.put("explicit", this.explicit);
        toDb.put("premium", this.premium);

        List<Document> imageArray = new ArrayList<>();
        for(MiniImage i : this.images) {
            imageArray.add(i.toMongo());
        }

        toDb.put("images", imageArray);
        toDb.put("releaseDate", releaseDate);

        List<Document> trackArray = new ArrayList<>();
        for(MiniTrack t : this.tracks) {
            trackArray.add(t.toMongo());
        }
        toDb.put("tracks", trackArray);
        return toDb;
    }
}
