package me.hyname.model;

import com.google.gson.JsonObject;
import org.bson.Document;

import javax.print.Doc;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
public enum Genre {
    POP("pop", "Pop"),
    ALTERNATIVE("alternative", "Alternative"),
    ELECTRONIC("electronic", "Electronic"),
    HIP_HOP("hip-hop", "Hip-Hop"),
    ROCK("rock", "Rock"),
    RAP("rap", "Rap"),
    COUNTRY("country", "Country"),
    R_AND_B("rnb", "R&B"),
    JAZZ("jazz", "Jazz"),
    CLASSICAL("classical", "Classical"),
    REGGAE("reggae", "Reggae"),
    K_POP("k-pop", "K-Pop"),
    SOUNDTRACK("soundtrack", "Soundtrack"),
    SINGER_SONGWRITER("singer-songwriter", "Singer/Songwriter"),
    WORLDWIDE("worldwide", "Worldwide"),
    NEW_AGE("new-age", "New Age"),
    J_POP("j-pop", "J-Pop"),
    INSTRAUMENTAL("instrumental", "Instrumental"),
    DANCE("dance", "Dance"),
    ELECTRONICA("electronica", "Electronica");

    @XmlElement(name = "id")
    String id;

    @XmlElement(name = "title")
    String title;

    Genre(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public static Genre fromMongo(Document document) {
        for (Genre genre : values()) {
            if (genre.id.equals(document.getString("id"))) {
                return genre;
            }
        }
        throw new NullPointerException("Genre \"" + document.getString("id") + "\" not found!");
    }

    public Document toMongo() {
        Document document = new Document();

        document.put("id", this.id);
        document.put("title", this.title);

        return document;
    }
}
