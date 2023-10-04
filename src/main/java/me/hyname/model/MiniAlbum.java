package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;


@XmlRootElement(name = "album")
@AllArgsConstructor
@NoArgsConstructor
public class MiniAlbum {

    @XmlElement(name = "title")
    public String title;

    @XmlElement(name = "id")
    public UUID id;

    public static MiniAlbum fromMongo(Document album) {
        return new MiniAlbum(album.getString("title"), album.get("id", UUID.class));
    }


    public Document toMongo() {
        Document document = new Document();

        document.put("title", this.title);
        document.put("id", this.id);

        return document;
    }
}
