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
import java.util.*;

public class GETArtistBiography implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Biography.class, Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Artist artist = Main.getStorage().readArtist(request.params(":id").toLowerCase());
        Biography bio = new Biography(new Date(System.currentTimeMillis()), artist.getName(), artist.getDbId(), artist.getBio(), "Microsoft Corporation");
        Feed<Biography> que=  new Feed<>();

        que.setEntries(Collections.singletonList(bio));




        marshallerObj.marshal(que, baos);
        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
