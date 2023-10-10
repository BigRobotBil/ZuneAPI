package me.hyname.route.misc;

import spark.Request;
import spark.Response;
import spark.Route;

public class GETHomeRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("text/plain");
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
