package me.hyname.model;

import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mood")
public enum Mood {
    UPBEAT("upbeat", "Upbeat"),
    JOYFUL("joyful", "Joyful");

    @XmlElement(name = "id")
    String id;

    @XmlElement(name = "title")
    String title;

    Mood(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public static Mood fromMongo(Document document) {
        for (Mood mood : values()) {
            if (mood.id.equals(document.getString("id"))) {
                return mood;
            }
        }
        throw new NullPointerException("Mood \"" + document.getString("id") + "\" not found!");
    }

    public Document toMongo() {
        Document document = new Document();

        document.put("id", this.id);
        document.put("title", this.title);

        return document;
    }
}
