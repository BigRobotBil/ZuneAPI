package me.hyname.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "link", namespace = "http://schemas.zune.net/catalog/music/2007/10")
public class MediaLink {
    @XmlElement(name = "type")
    public String type;

    @XmlElement(name = "target")
    public String target;
}
