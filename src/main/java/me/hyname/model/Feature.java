package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "entry")
public class Feature {
    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "link")
    private MediaLink link;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "text")
    private String text;

    @XmlElement(name = "sequenceNumber")
    private int sequenceNumber;

    @XmlElement(name = "image")
    private MiniImage image;

    @XmlElement(name = "backgroundImage")
    private MiniImage backgroundImage;

    @XmlElement(name = "isExplicit")
    private boolean isExplicit;
}
