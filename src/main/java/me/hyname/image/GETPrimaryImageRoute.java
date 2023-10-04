package me.hyname.image;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GETPrimaryImageRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        File file = new File("data/" + request.params(":id").toLowerCase() + "/0.jpg");
        response.type("image/jpeg");
        InputStream is = new FileInputStream(file);

        response.raw().setContentType("image/jpeg");
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
