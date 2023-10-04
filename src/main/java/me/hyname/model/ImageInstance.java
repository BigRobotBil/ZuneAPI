package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement(name = "imageInstance", namespace = "http://schemas.zune.net/catalog/music/2007/10")
@AllArgsConstructor
@NoArgsConstructor
public class ImageInstance {

    @XmlElement(name = "id")
    private UUID id;

    @XmlElement(name = "url")
    private String url;

    @XmlElement(name = "format")
    private String format;

    @XmlElement(name = "width")
    private int width;

    @XmlElement(name = "height")
    private int height;

    public static ImageInstance fromMongo(Document instance) {
        UUID id = UUID.fromString(instance.getString("id"));
        String url = instance.getString("url");
        String format = instance.getString("format");
        int width = instance.getInteger("width");
        int height = instance.getInteger("height");
        return new ImageInstance(id, url, format, width, height);
    }

    public Document toMongo() {
        Document document = new Document();

        document.put("id", this.id.toString());
        document.put("url", this.url);
        document.put("format", this.format);
        document.put("width", this.width);
        document.put("height", this.height);

        return document;
    }
}
