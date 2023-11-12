package me.hyname.route.image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.route.AbstractRoute;
import me.hyname.storage.Storage;

public class GETPrimaryImageRoute extends AbstractRoute {

    public GETPrimaryImageRoute(Storage storage, JAXBContext jaxb) {
        super(storage, jaxb);
    }

    @Override
    public String handle(Map<ParamEnum, String> params) {
        String id = params.getOrDefault(ParamEnum.ID, "");
        try {
            return fetchItem(id);
        } catch (IOException e) {
            logger.error("Failed to capture image information for '" + id + "' when fetching Primary Image Route", e);
            return "";
        }
    }

    private String fetchItem(String id) throws IOException {
        File file = new File("image/primary/" + id + ".jpg");
        InputStream is = new FileInputStream(file);
        OutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        String result = out.toString();
        is.close();
        return result.trim();
    }
}
