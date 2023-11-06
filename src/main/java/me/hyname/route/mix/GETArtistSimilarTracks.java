package me.hyname.route.mix;

import me.hyname.Main;
import me.hyname.model.*;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: TEST ME

public class GETArtistSimilarTracks implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Artist primaryArtist = Main.getStorage().readArtist(request.params(":id").toLowerCase());
        List<Track> documents = Main.getStorage().getTracks();
        List<Track> similarTracks = new ArrayList<>();

        for(Track t : documents) {
            Artist trackArtist = Main.getStorage().readArtist(t.albumArtist.id.toString());
            if (t.albumArtist.id == primaryArtist.id) {
                similarTracks.add(t);
            } else {
                for (Genre genre : trackArtist.getArtistGenres()) {
                    for (Genre otherGenres : primaryArtist.getArtistGenres()) {
                        if (genre == primaryArtist.getArtistPrimaryGenre() || otherGenres == trackArtist.getArtistPrimaryGenre()) {
                            if(similarTracks.contains(t)) continue;
                            similarTracks.add(t);
                            break;
                        }
                    }
                }
            }
        }

        Feed<Track> results = new Feed<>();

        results.setEntries(similarTracks);

        marshallerObj.marshal(results, baos);

        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
