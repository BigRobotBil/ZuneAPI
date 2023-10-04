package me.hyname.store;

import me.hyname.model.*;

import java.util.*;

public class ArtistStorage {
    public static Artist taylorSwift;
    public static Album ts1989;

    public ArtistStorage() {
        taylorSwift = new Artist(
                "Taylor Swift",
                "Taylor Swift",
                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"),
                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"),
                10,
                false,
                "/v3.2/en-US/music/artist/10450000-0200-11db-89ca-0019b92a3933/biography/",
                "aaa",
                "bbb",
                1000,
                Genre.POP,
                Arrays.asList(Genre.POP, Genre.ALTERNATIVE, Genre.COUNTRY),
                Arrays.asList(Mood.UPBEAT, Mood.JOYFUL),
                new Image(UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"), Arrays.asList(
                        new ImageInstance(
                                UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"),
                                "/v3.2/image/f77adb33-8917-4c42-bfb3-f32a6f9d96b5",
                                "jpg",
                                320,
                                300
                        ))),
//                new Image(UUID.fromString("eaae2200-60e0-41a0-9827-0fa21a7950fb"), Arrays.asList(
//                        new ImageInstance(
//                                UUID.fromString("eaae2200-60e0-41a0-9827-0fa21a7950fb"),
//                                "/v3.2/image/eaae2200-60e0-41a0-9827-0fa21a7950fb",
//                                "jpg",
//                                480,
//                                700
//                        ))),
                new MiniImage(UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")),
                false,
                Arrays.asList(
                        new MiniImage(UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"))
//                        new Image(UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"), Arrays.asList(
//                        new ImageInstance(
//                                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"),
//                                "/v3.2/image/10450000-0200-11db-89ca-0019b92a3933",
//                                "jpg",
//                                320,
//                                320
//                        ))),
//                        new Image(UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"), Arrays.asList(
//                        new ImageInstance(
//                                UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"),
//                                "/v3.2/en-US/music/album/f77adb33-8917-4c42-bfb3-f32a6f9d96b5/image",
//                                "jpg",
//                                320,
//                                300
//                        )))
                )
                );



        ts1989 = new Album(
                "1989",
                "1989",
                UUID.fromString("ea1ecbf2-baaf-4a48-a2b4-13d3c79d0530"),
                new MiniArtist("Taylor Swift",
                        UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")
                ),
                Genre.POP,
                10,
                false,
                false,
                Arrays.asList(
                        new MiniImage(UUID.fromString("f77adb33-8917-4c42-bfb3-f32a6f9d96b5"))
                ),
                new Date(1414373526000l),
                Arrays.asList(
                        new MiniTrack("Welcome To New York", "Welcome To New York", UUID.randomUUID(), 1),
                        new MiniTrack("Blank Space", "Blank Space", UUID.randomUUID(), 2),
                        new MiniTrack("Style", "Style", UUID.randomUUID(), 3),
                        new MiniTrack("Out of the Woods", "Out of the Woods", UUID.randomUUID(), 4),
                        new MiniTrack("Shake It Off", "Shake It Off", UUID.randomUUID(), 6),
                        new MiniTrack("Bad Blood", "Bad Blood", UUID.randomUUID(), 8),
                        new MiniTrack("Wildest Dreams", "Wildest Dreams", UUID.randomUUID(), 9)
                )
        );
    }
}
