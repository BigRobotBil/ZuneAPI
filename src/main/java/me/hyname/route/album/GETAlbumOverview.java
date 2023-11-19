package me.hyname.route.album;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import me.hyname.enums.ParamEnum;
import me.hyname.model.Album;
import me.hyname.route.AbstractRoute;
import me.hyname.storage.Storage;

/**
 * Returns album overview information
 */
public class GETAlbumOverview extends AbstractRoute {

    public GETAlbumOverview(Storage storage, JAXBContext jaxb) {
        super(storage, jaxb);
    }

    @Override
    public byte[] handle(Map<ParamEnum, String> params) {
        String id = params.getOrDefault(ParamEnum.ID, "");
        try {
            ByteArrayOutputStream baos = fetchItem(id);

            return baos.toByteArray();
        } catch (JAXBException e) {
            logger.error("Failed to marshal XML information for Album '" + id + "' when fetching Overview", e);
            return errorGen.generateErrorResponse(500, e.getMessage(), "");
        }
    }

    /**
     * Fetch the album over information from storage
     * @param id UUID of the album being fetched
     * @return ByteArray of marshalled XML information
     * @throws JAXBException failure to marshal XML
     */
    private ByteArrayOutputStream fetchItem(String id) throws JAXBException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        Marshaller marshallerObj = jaxb.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        Album que = storage.readAlbum(id.toLowerCase());
        marshallerObj.marshal(que, result);

        return result;
    }
}
