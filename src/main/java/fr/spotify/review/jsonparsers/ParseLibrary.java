package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.Track;
import fr.spotify.review.services.AlbumService;
import fr.spotify.review.services.ArtistService;
import fr.spotify.review.services.TrackService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class ParseLibrary {

    public static void parseLibrary(TrackService trackService, AlbumService albumServ, ArtistService artistService) throws SQLException {
        JSONArray tracks= null, albums=null, episodes=null, shows=null;
        JSONObject library=null;
        JSONTokener playlist = null;
        //Open the File
        try {
            playlist = new JSONTokener(new FileReader("/mnt/docker/MyData/YourLibrary.json"));
            library = new JSONObject(playlist);
            tracks = library.getJSONArray("tracks");
            albums = library.getJSONArray("albums");
            episodes = library.getJSONArray("episodes");
            shows = library.getJSONArray("shows");
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<tracks.length(); i++)
        {
            JSONObject jsonObj=(JSONObject) tracks.get(i);
            Artist artist = artistService.findByName(jsonObj.getString("artist"));
            if (artist==null) {
                artist = new Artist(jsonObj.getString("artist"));
                artistService.save(artist);
            }

            Album album = albumServ.findByName(jsonObj.getString("album"));
            if (album==null) {
                album = new Album(jsonObj.getString("album"), artist);
                albumServ.save(album);
            }
            Track title = trackService.findByTrackURI(jsonObj.getString("uri"));
            if (title==null) {
                title = new Track( jsonObj.getString("track"), jsonObj.getString("uri"), album);
                trackService.save(title);
            }

        }
    }
}