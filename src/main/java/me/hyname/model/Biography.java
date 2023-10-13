package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "entry", namespace = "http://www.w3.org/2005/Atom")
public class Biography {
    @XmlElement(name = "updated")
    Date updated;

    @XmlElement(name = "title")
    String title;

    @XmlElement(name = "id")
    UUID id;
    @XmlElement(name = "content")
    String content;

    @XmlElement(name = "author")
    String authorName;
}

