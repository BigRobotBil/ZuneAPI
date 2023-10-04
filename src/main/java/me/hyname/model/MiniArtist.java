package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "artist")
public class MiniArtist {

    @XmlElement(name = "name")
    public String title;

    @XmlElement(name = "id")
    public UUID id;

    public static MiniArtist fromMongo(Document artist) {
        return new MiniArtist(artist.getString("title"), UUID.fromString(artist.getString("id")));
    }


    public Document toMongo() {
        Document document = new Document();

        document.put("name", this.title);
        document.put("id", this.id.toString());

        return document;
    }
}
