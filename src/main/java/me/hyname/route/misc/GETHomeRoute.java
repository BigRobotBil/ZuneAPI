package me.hyname.route.misc;

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

public class GETHomeRoute extends AbstractRoute {

    public GETHomeRoute(Storage storage, JAXBContext jaxb) {
        super(storage, jaxb);
    }

    @Override
    public String handle(Map<ParamEnum, String> params) {
        return
        "                            __               _ \n" +
        " ____  __  ______  ___     / /  ____ _____  (_)\n" +
        "/_  / / / / / __ \\/ _ \\   / /  / __ `/ __ \\/ / \n" +
        " / /_/ /_/ / / / /  __/  / /  / /_/ / /_/ / /  \n" +
        "/___/\\__,_/_/ /_/\\___/  / /   \\__,_/ .___/_/   \n" +
        "                       /_/        /_/          \n"+
        "\"don't be anti-social\"\n" +
        "\n" +
        "git:                    https://github.com/hynamedev/zuneapi\n" +
        "contributors:           https://github.com/hynamedev/zuneapi/contributors\n" +
        "zune community discord: https://discord.gg/MdH8fyGn8t";

    }
}
