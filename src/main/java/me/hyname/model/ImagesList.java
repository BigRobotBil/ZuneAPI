package me.hyname.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "feed")
public class ImagesList {
    @XmlElement(name = "images")
    List<Image> images;
}
