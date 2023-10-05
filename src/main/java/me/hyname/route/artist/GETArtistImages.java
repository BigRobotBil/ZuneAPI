package me.hyname.route.artist;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GETArtistImages implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        File file = new File("data/" + request.params(":id") + "/images.xml");
        response.type("text/xml");
        InputStream is = new FileInputStream(file);

        response.raw().setContentType("text/xml");
        response.raw().setHeader("Content-Disposition", "inline; filename=" + file.getName());

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > 0) {
            response.raw().getOutputStream().write(buffer, 0, len);
        }

        String result = IOUtils.toString(is);
        is.close();
        System.out.println(request.url() + " | " + request.contextPath() + " | " + request.params() + " | " + request.queryParams() + " | " + request.queryString());
        return result.trim();
    }
}
