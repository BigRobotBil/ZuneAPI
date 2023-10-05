package me.hyname.route.artist;

import me.hyname.Main;
import me.hyname.model.Artist;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class GETArtistOverview2 implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Artist.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Artist que= ArtistStorage.taylorSwift;
        Artist que = Main.getStorage().readArtist(request.params(":id").toLowerCase());
        marshallerObj.marshal(que, baos);

        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
