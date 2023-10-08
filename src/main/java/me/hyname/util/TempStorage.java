package me.hyname.util;

import me.hyname.model.*;

import java.util.*;

// literally just here for debugging
public class TempStorage {
    public static Artist taylorSwift;
    public static Album ts1989;
    public static Album tsRed;
    public static Album tsRep;

    public TempStorage() {
        taylorSwift = new Artist(
                "Taylor Swift",
                "Taylor Swift",
                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"),
                UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"),
                10,
                false,
                "/v3.2/en-US/music/artist/10450000-0200-11db-89ca-0019b92a3933/biography/",
                "bbb",
                1000,
                Genre.POP,
                Arrays.asList(Genre.POP, Genre.ALTERNATIVE, Genre.COUNTRY),
                Arrays.asList(Mood.UPBEAT, Mood.JOYFUL),
                new MiniImage(UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")),
                false,
                Arrays.asList(
                        new MiniImage(UUID.fromString("10450000-0200-11db-89ca-0019b92a3933"))
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
                        new MiniTrack("Welcome To New York", "Welcome To New York", UUID.randomUUID(), 1, true, false, 213, 0),
                        new MiniTrack("Blank Space", "Blank Space", UUID.randomUUID(), 2, true, false, 213, 0),
                        new MiniTrack("Style", "Style", UUID.randomUUID(), 3, true, false, 213, 0),
                        new MiniTrack("Out of the Woods", "Out of the Woods", UUID.randomUUID(), 4, true, false, 213, 0),
                        new MiniTrack("Shake It Off", "Shake It Off", UUID.randomUUID(), 6, true, false, 213, 0),
                        new MiniTrack("Bad Blood", "Bad Blood", UUID.randomUUID(), 8, true, false, 213, 0),
                        new MiniTrack("Wildest Dreams", "Wildest Dreams", UUID.randomUUID(), 9, true, false, 213, 0)
                )
        );

        tsRed = new Album(
                "Red",
                "Red",
                UUID.fromString("b1fef50e-eb67-4419-93f6-f22a03551a6d"),
                new MiniArtist("Taylor Swift",
                        UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")
                ),
                Genre.COUNTRY,
                10,
                false,
                false,
                Arrays.asList(
                        new MiniImage(UUID.fromString("b1fef50e-eb67-4419-93f6-f22a03551a6d"))
                ),
                new Date(1351313047000l),
                Arrays.asList(
                        new MiniTrack("State of Grace", "State of Grace", UUID.randomUUID(), 1, true, false, 295, 0),
                        new MiniTrack("Treacherous", "Treacherous", UUID.randomUUID(), 2, true, false, 213, 0),
                        new MiniTrack("I Knew You Were Trouble", "I Knew You Were Trouble", UUID.randomUUID(), 3, true, false, 213, 0),
                        new MiniTrack("All Too Well", "All Too Well", UUID.randomUUID(), 4, true, false, 213, 0),
                        new MiniTrack("22", "22", UUID.randomUUID(), 6, true, false, 213, 0),
                        new MiniTrack("I Almost Do", "I Almost Do", UUID.randomUUID(), 8, true, false, 213, 0),
                        new MiniTrack("We Are Never Ever Getting Back Together", "We Are Never Ever Getting Back Together", UUID.randomUUID(), 9, true, false, 213, 0)
                )
        );

        tsRep = new Album(
                "reputation",
                "reputation",
                UUID.fromString("6d4a5d4f-e785-4c8e-9ab6-97083803208f"),
                new MiniArtist("Taylor Swift",
                        UUID.fromString("10450000-0200-11db-89ca-0019b92a3933")
                ),
                Genre.POP,
                10,
                false,
                true,
                Arrays.asList(
                        new MiniImage(UUID.fromString("6d4a5d4f-e785-4c8e-9ab6-97083803208f"))
                ),
                new Date(1510275113000l),
                Arrays.asList(
                        new MiniTrack("...Ready for It?", "...Ready for It?", UUID.randomUUID(), 1, true, true, 295, 0),
                        new MiniTrack("End Game", "End Game", UUID.randomUUID(), 2, true, false, 213, 0),
                        new MiniTrack("I Did Something Bad", "I Did Something Bad", UUID.randomUUID(), 3, true, false, 213, 0),
                        new MiniTrack("Don't Blame Me", "Don't Blame Me", UUID.randomUUID(), 4, true, false, 213, 0),
                        new MiniTrack("Delicate", "Delicate", UUID.randomUUID(), 6, true, false, 213, 0),
                        new MiniTrack("Look What You Made Me Do", "Look What You Made Me Do", UUID.randomUUID(), 8, true, false, 213, 0),
                        new MiniTrack("So It Goes...", "So It Goes...", UUID.randomUUID(), 9, true, false, 213, 0)
                )
        );
    }
}