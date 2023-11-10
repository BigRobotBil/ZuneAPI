package me.hyname.route.artist;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import me.hyname.enums.OrderByEnum;
import me.hyname.model.Album;
import me.hyname.model.Artist;
import me.hyname.model.Feed;
import me.hyname.storage.Storage;

public class GETArtistAlbums {

    Logger logger = LogManager.getRootLogger();

    Storage storage;
    JAXBContext jaxb;

    public GETArtistAlbums(Storage storage, JAXBContext jaxb) {
        this.storage = storage;
        this.jaxb = jaxb;
    }

    public String handle(String id, String orderBy) {
        logger.trace("Received request for Artist '{}' with query param `{}`", id, orderBy);

        try {
            ByteArrayOutputStream baos = fetchItem(id, orderBy);

            if (baos.size() == 0) {
                logger.debug("Artist '{}' did not return any albums", id);
            }

            return baos.toString(Charset.defaultCharset().name());
        } catch (JAXBException e) {
            logger.error("Failed to marshal XML information for Artist '" + id + "' when fetching Album(s)", e);
            return "";
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to convert binary to XML for Artist '" + id + "' when fetching Album(s)", e);
            return "";
        }
    }

    /**
     * Fetch the album based on the submitted ID, ordering the albums based on the selected order type
     * @param id ID of the artist
     * @param orderBy Method by which to order the albums
     * @return XML representation of the albums in a (possibly) defined order
     * @throws JAXBException in the event that there is an error trying to marshal
     */
    private ByteArrayOutputStream fetchItem(String id, String orderBy) throws JAXBException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        Marshaller marshallerObj = jaxb.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Artist artist = storage.readArtist(id.toLowerCase());
        List<Album> albumList = storage.readAlbumsByArtist(artist);
        Feed<Album> que = new Feed<>();

        albumList.sort(Comparator.comparing(o -> o.releaseDate));
        Collections.reverse(albumList);

        OrderByEnum order = EnumUtils.getEnum(OrderByEnum.class, orderBy);

        if (order != null) {
            switch (order) {
                case TITLE:
                    albumList.sort(Comparator.comparing(o -> o.title));
                    break;
                case RELEASEDATE:
                    albumList.sort(Comparator.comparing(o -> o.releaseDate));
                    Collections.reverse(albumList);
                    break;
                case PLAYRANK:
                    albumList.sort(Comparator.comparingInt(o -> o.popularity));
                    break;
            }
        } else {
            logger.trace("Query for albums for Artist '{}' did not provide an orderBy query param");
        }

        que.setEntries(albumList);

        marshallerObj.marshal(que, result);

        return result;
    }
}
