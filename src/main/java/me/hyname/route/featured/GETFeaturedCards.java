package me.hyname.route.featured;

import me.hyname.model.*;
import me.hyname.store.ArtistStorage;
import spark.Request;
import spark.Response;
import spark.Route;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class GETFeaturedCards implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class, Feature.class, MiniImage.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Feed<Feature> que=  new Feed<>();
        List<Feature> albums = ArtistStorage.features;

        que.setEntries(albums);




        marshallerObj.marshal(que, baos);

        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
