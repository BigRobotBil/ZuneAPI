package me.hyname.route.artist;

import me.hyname.model.*;
import me.hyname.storage.Storage;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Fetch any/all tracks related to the requested artist
 */
public class GETArtistTracks {

    Logger logger = LogManager.getRootLogger();

    Storage storage;
    JAXBContext jaxb;

    public GETArtistTracks(Storage storage, JAXBContext jaxb) {
        this.storage = storage;
        this.jaxb = jaxb;
    }

    /**
     * Handles the request to get tracks for a specific artist, based on UUID
     * 
     * @param id The ID of the artist
     * @return XML formatted document containing a list of tracks
     */
    public String handle(@PathVariable String id) {
        logger.trace("Received request for Artist '{}'", id);

        try {
            ByteArrayOutputStream baos = fetchItem(id);
            
            if (baos.size() == 0) {
                logger.debug("Artist '{}' did not return any tracks", id);
            }

            return baos.toString(Charset.defaultCharset().name());
        } catch (JAXBException e) {
            logger.error("Failed to marshal XML information for Artist '" + id + "' when fetching Track(s)", e);
            return "";
        }
        catch (UnsupportedEncodingException e) {
            logger.error("Failed to convert binary to XML for Artist '" + id + "' when fetching Track(s)", e);
            return "";
        }
    }

    /**
     * Fetch all of the tracks under a specific artist
     * 
     * @param id Artist UUID
     * @return XML ByteArray containing all of the track information
     * @throws JAXBException
     */
    private ByteArrayOutputStream fetchItem(String id) throws JAXBException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        Marshaller marshallerObj = jaxb.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Artist artist = storage.readArtist(id.toLowerCase());

        // ensure artist actually exists
        if (artist != null && artist.id != null) {
            Feed<Track> que = new Feed<>();
            que.setEntries(storage.readTracksByArtist(artist));

            marshallerObj.marshal(que, result);
        }
        
        return result;
    }
}