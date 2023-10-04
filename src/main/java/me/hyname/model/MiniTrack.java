package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "entry")
public class MiniTrack {

    @XmlElement(name = "title")
    public String title;

    @XmlElement(name = "sortTitle")
    public String sortTitle;

    @XmlElement(name = "id")
    public UUID id;

    @XmlElement(name = "trackNumber")
    public int trackNumber;

    public static MiniTrack fromMongo(Document track) {
        return new MiniTrack(track.getString("title"), track.getString("sortTitle"), UUID.fromString(track.getString("id")), track.getInteger("trackNumber"));
    }


    public Document toMongo() {
        Document document = new Document();

        document.put("title", this.title);
        document.put("id", this.id.toString());
        document.put("sortTitle", this.sortTitle);
        document.put("trackNumber", this.trackNumber);

        return document;
    }
}
