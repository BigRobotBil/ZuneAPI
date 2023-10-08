package me.hyname.model;

import lombok.NoArgsConstructor;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@XmlRootElement(name = "entry")
public class GenreInstance {
    @XmlElement(name = "id")
    public
    String id;

    @XmlElement(name = "title")
    public
    String title;

    public GenreInstance(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}

