package me.hyname.route.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.imageio.ImageIO;

import jakarta.xml.bind.JAXBContext;
import me.hyname.enums.ParamEnum;
import me.hyname.route.AbstractRoute;
import me.hyname.storage.Storage;

public class GETDeviceBackgroundImage extends AbstractRoute {

    public GETDeviceBackgroundImage(Storage storage, JAXBContext jaxb) {
        super(storage, jaxb);
    }

    @Override
    public String handle(Map<ParamEnum, String> params) {
        String id = params.getOrDefault(ParamEnum.ID, "");
        try {
            return fetchItem(id).toString();
        } catch (IOException e) {
            logger.error("Failed to capture image information for '" + id + "' when fetching Device Background Image", e);
            return "";
        }
    }

    private ByteArrayOutputStream fetchItem(String id) throws IOException {
        // File file = new File("image/background/" + id + ".jpg");
        // InputStream is = new FileInputStream(file);
        // OutputStream out = new ByteArrayOutputStream();

        // byte[] buffer = new byte[1024];
        // int len;
        // while ((len = is.read(buffer)) > 0) {
        //     out.write(buffer, 0, len);
        // }

        // String result = out.toString();
        // is.close();

        // return result.trim();
        try (FileInputStream fileInStr = new FileInputStream("image/background/" + id + ".jpg")) {
            BufferedImage image = ImageIO.read(fileInStr);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);

            return baos;
        }
    }
}
