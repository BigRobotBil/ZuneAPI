package me.hyname.model;

import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "error")
public class ErrorResponse {


    @XmlElement(name = "status")
    private int id;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "causedBy")
    private String causedBy;

    public ErrorResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ErrorResponse(int id, String description, String causedBy) {
        this.id = id;
        this.description = description;
        this.causedBy = causedBy;
    }

    public ErrorResponse() {

    }
}
