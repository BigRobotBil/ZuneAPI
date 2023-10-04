package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class Image {


    @XmlElement(name = "id")
    private UUID id;

    @XmlElementWrapper(name = "instances", namespace = "http://schemas.zune.net/catalog/music/2007/10")
    @XmlElement(name = "imageInstance")
    private List<ImageInstance> instances;

    public static Image fromMongo(Document albumImage) {
        UUID id = UUID.fromString(albumImage.getString("id"));
        List<ImageInstance> instances = new ArrayList<>();
        for(Document instance : albumImage.getList("instances", Document.class)) {
            instances.add(ImageInstance.fromMongo(instance));
        }
        return new Image(id, instances);
    }

    public Document toMongo() {
        Document document = new Document();

        document.put("id", this.id.toString());
        List<Document> instanceArray = new ArrayList<>();
        for(ImageInstance ins : this.instances) {
            instanceArray.add(ins.toMongo());
        }
        document.put("instances", instanceArray);

        return document;
    }
}
