package me.hyname.route.artist;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import me.hyname.Main;
import me.hyname.model.Artist;
import me.hyname.model.MiniAlbum;
import me.hyname.model.MiniArtist;
import me.hyname.model.Track;
import me.hyname.storage.Storage;
import spark.Request;
import spark.Response;

public class GETArtistTracksTest {

    GETArtistTracks target = new GETArtistTracks();

    Storage mockStorage;

    @BeforeEach
    public void setup() {
        mockStorage = mock(Storage.class);
        Main.setStorage(mockStorage);
    }

    @Test
    public void handleMessage() throws Exception {
        Request mockReq = mock(Request.class);
        Response mockRes = mock(Response.class);
        HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
        when(mockRes.raw()).thenReturn(mockHttpServletResponse);
        when(mockReq.params(":id")).thenReturn(UUID.randomUUID().toString());

        Artist artist = new Artist();
        artist.id = UUID.randomUUID();
        List<Track> tracks = new ArrayList<>();
        Track trackOne = genTrack();
        Track trackTwo = genTrack();
        Track trackThree = genTrack();

        tracks.add(trackOne);
        tracks.add(trackTwo);
        tracks.add(trackThree);

        when(mockStorage.readArtist(anyString())).thenReturn(artist);
        when(mockStorage.readTracksByArtist(artist)).thenReturn(tracks);

        String result = (String) target.handle(mockReq, mockRes);

        // Should probably actually parse the XML and ensure it's valid vs cheating through just the IDs being in there
        assertTrue(result.contains(trackOne.id.toString()));
        assertTrue(result.contains(trackTwo.id.toString()));
        assertTrue(result.contains(trackThree.id.toString()));

        verify(mockRes).type("text/xml");
        verify(mockHttpServletResponse).setContentType("text/xml");
    }

    private Track genTrack() {
        Track result = new Track();
        
        // mini album
        result.album = new MiniAlbum();
        result.album.id = UUID.randomUUID();
        result.album.title = "A Cool Title for a Mini Album - " + result.album.id;

        // mini artist
        result.albumArtist = new MiniArtist();
        result.albumArtist.id = UUID.randomUUID();
        result.albumArtist.title = "A Cool Title for a Mini Artist - " + result.albumArtist.id;

        result.canBurn = false;
        result.canDownload = false;
        result.canPlay = false;
        result.canPurchase = false;
        result.canPurchaseAlbumOnly = false;
        result.canPurchaseMP3 = false;
        result.canSync = true;
        result.discNumber = 1;
        result.id = UUID.randomUUID();
        result.inCollection = true;
        result.isDownloading = false;
        result.lengthSeconds = 60;
        result.musicVideoId = UUID.randomUUID();
        result.playCount = 5;
        result.pointsPrice = 0;
        result.sortTitle = "A Sorted Title";
        result.title = "A Cool Title for a Track - " + result.id;
        result.trackNumber = 1;

        return result;
    }
}
