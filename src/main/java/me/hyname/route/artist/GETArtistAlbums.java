package me.hyname.route.artist;

import me.hyname.Main;
import me.hyname.model.*;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GETArtistAlbums implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Artist artist = Main.getStorage().readArtist(request.params(":id").toLowerCase());
        List<Album> albumList = Main.getStorage().readAlbumsByArtist(artist);
        Feed<Album> que=  new Feed<>();

        if(request.queryParams("orderby") != null) {
            switch (request.queryParams("orderby")) {
                case "Title":
                    albumList.sort(Comparator.comparing(o -> o.title));
                    break;
                case "ReleaseDate":
                    albumList.sort(Comparator.comparing(o -> o.releaseDate));
                    Collections.reverse(albumList);
                    break;
                case "PlayRank":
                    albumList.sort(Comparator.comparingInt(o -> o.popularity));
                    break;
            }
        }
        que.setEntries(albumList);




        marshallerObj.marshal(que, baos);

        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
