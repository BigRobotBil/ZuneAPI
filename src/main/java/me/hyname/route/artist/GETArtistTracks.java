package me.hyname.route.artist;

import me.hyname.Main;
import me.hyname.model.*;
import spark.Request;
import spark.Response;
import spark.Route;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Fetch any/all tracks related to the requested artist
 */
public class GETArtistTracks implements Route {

    Logger logger = LogManager.getRootLogger();

    /**
     * Handles the request to get tracks for a specific artist, based on UUID
     * 
     * @param request The request for an Artist's tracks
     * @param response Response containing the tracks in XML format
     * @return XML formatted document containing a list of tracks
     */
    @Override
    public Object handle(Request request, Response response) {

        response.type("text/xml");
        response.raw().setContentType("text/xml");

        try {
            ByteArrayOutputStream baos = fetchItem(request);

            logger.debug(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
            
            if (baos.size() == 0) {
                logger.debug("Artist '{}' did not return any tracks", request.params(":id"));
            }

            return baos.toString(Charset.defaultCharset().name());
        } catch (JAXBException e) {
            logger.error("Failed to marshal XML information for Artist '" + request.params(":id") + "' when fetching Track(s)", e);
            return "";
        }
        catch (UnsupportedEncodingException e) {
            logger.error("Failed to convert binary to XML for Artist '" + request.params(":id") + "' when fetching Track(s)", e);
            return "";
        }
    }

    /**
     * Fetch all of the tracks under a specific artist
     * 
     * @param request Request containing the artist to fetch information for
     * @return XML ByteArray containing all of the track information
     * @throws JAXBException
     */
    private ByteArrayOutputStream fetchItem(Request request) throws JAXBException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        JAXBContext contextObj = JAXBContext.newInstance(Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Artist artist = Main.getStorage().readArtist(request.params(":id").toLowerCase());

        // ensure artist actually exists
        if (artist != null && artist.id != null) {
            Feed<Track> que = new Feed<>();
            que.setEntries(Main.getStorage().readTracksByArtist(artist));

            marshallerObj.marshal(que, result);
        }
        
        return result;
    }
}
