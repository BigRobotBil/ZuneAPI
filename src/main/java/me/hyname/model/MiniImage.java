package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class MiniImage {


    @XmlElement(name = "id")
    private UUID id;

    public static MiniImage fromMongo(Document imageDoc) {
        return new MiniImage(UUID.fromString(imageDoc.getString("id")));
    }

    public Document toMongo() {
        Document document = new Document();

        document.put("id", this.id.toString());

        return document;
    }
}
