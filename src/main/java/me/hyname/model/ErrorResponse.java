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

@XmlRootElement(name = "error")
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {


    @XmlElement(name = "status")
    private int id;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "causedBy")
    private String causedBy;
}
