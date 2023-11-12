package me.hyname.route.genre;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import me.hyname.enums.ParamEnum;
import me.hyname.model.Feed;
import me.hyname.model.Genre;
import me.hyname.model.GenreInstance;
import me.hyname.route.AbstractRoute;
import me.hyname.storage.Storage;

public class GETGenres extends AbstractRoute {

    public GETGenres(Storage storage, JAXBContext jaxb) {
        super(storage, jaxb);
    }

    @Override
    public String handle(Map<ParamEnum, String> params) {
        try {
            ByteArrayOutputStream baos = fetchItem();

            return baos.toString(Charset.defaultCharset().name());
        } catch (JAXBException e) {
            logger.error("Failed to marshal XML information for Genres", e);
            return "";
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to convert binary to XML for Genres", e);
            return "";
        }
    }

    private ByteArrayOutputStream fetchItem() throws JAXBException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        Marshaller marshallerObj = jaxb.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Feed<GenreInstance> que = new Feed<>();
        List<GenreInstance> genres = new ArrayList<>();

        for (Genre g : Genre.values()) {
            genres.add(new GenreInstance(g.id, g.title));
        }

        que.setEntries(genres);

        marshallerObj.marshal(que, baos);

        return result;
    }
}
