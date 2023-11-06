package me.hyname.route.album;

import me.hyname.Main;
import me.hyname.model.Album;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class GETAlbumOverview implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.type("text/xml");
        response.raw().setContentType("text/xml");
        JAXBContext contextObj = JAXBContext.newInstance(Album.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Artist que= ArtistStorage.taylorSwift;
        Album que = Main.getStorage().readAlbum(request.params(":id").toLowerCase());
        marshallerObj.marshal(que, baos);

        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return baos.toString(Charset.defaultCharset().name());
    }
}
