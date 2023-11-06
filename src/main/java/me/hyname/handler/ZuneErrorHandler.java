package me.hyname.handler;

import me.hyname.model.*;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class ZuneErrorHandler implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request request, Response response) {
        response.status(500);
        response.type("text/xml");
        response.raw().setContentType("text/xml");

        JAXBContext contextObj = null;


        System.out.println("API Encountered an error.");
        System.out.println("Caused By: " + request.url());
        System.out.println("Stacktrace:");
        e.printStackTrace();

        try {
            contextObj = JAXBContext.newInstance(ErrorResponse.class, Feed.class, Album.class, MiniAlbum.class, MiniArtist.class, MiniImage.class, Track.class, Artist.class, Genre.class);

            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ErrorResponse err = new ErrorResponse(500, e.getMessage(), request.contextPath());

            marshallerObj.marshal(err, baos);
            response.body(baos.toString(Charset.defaultCharset().name()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return;

    }
}
