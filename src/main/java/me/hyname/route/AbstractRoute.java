package me.hyname.route;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.storage.Storage;

public abstract class AbstractRoute {
    public Logger logger = LogManager.getRootLogger();

    protected Storage storage;
    public JAXBContext jaxb;

    public AbstractRoute(Storage storage, JAXBContext jaxb) {
        this.storage = storage;
        this.jaxb = jaxb;
    }

    public abstract String handle(Map<ParamEnum, String> params);
}
