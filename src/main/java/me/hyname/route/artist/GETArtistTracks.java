package me.hyname.route.artist;

import me.hyname.model.*;
import me.hyname.storage.Storage;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Fetch any/all tracks related to the requested artist
 */
@RestController
public class GETArtistTracks {

    Logger logger = LogManager.getRootLogger();

    Storage storage;

    public GETArtistTracks(Storage storage) {
        this.storage = storage;
    }

    /**
     * Handles the request to get tracks for a specific artist, based on UUID
     * 
     * @param id The ID of the artist
     * @return XML formatted document containing a list of tracks
     */
    @RequestMapping(value = "/*/*/music/artist/{id}/tracks/", method = RequestMethod.GET)
    public String handle(@PathVariable String id) {

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
        JAXBContext contextObj = JAXBContext.newInstance(Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
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