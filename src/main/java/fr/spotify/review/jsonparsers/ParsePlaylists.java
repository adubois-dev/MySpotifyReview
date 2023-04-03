package fr.spotify.review.jsonparsers;

import fr.spotify.review.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParsePlaylists {

    public static void parsePlaylists() throws SQLException {
        //PARSE StreamingHistory;
        JSONParser jsonP = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        JSONArray playlists = null;
        JSONObject playlist = null;
        //Open the File
        try {
            playlist = (JSONObject) jsonP.parse(new FileReader("MyData/Playlist1.json"));
            playlists =(JSONArray) playlist.get("playlists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Historics> historyList = new ArrayList<Historics>();
        //Parse i
        int len = playlists.size();

        for (int i = 0; i < len; i++) {
            JSONObject singlePlaylist = (JSONObject) playlists.get(i);
            Date lastModifiedDate = null;
            DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                lastModifiedDate = parseFormat.parse((String) singlePlaylist.get("lastModifiedDate"));
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }

            JSONArray tracks = (JSONArray) singlePlaylist.get("items");
            Playlist myPlaylist = new Playlist((String) singlePlaylist.get("name"), User.getUserByEmail("adubois.personnel@gmail.com"), lastModifiedDate, (String) singlePlaylist.get("description"), Long.valueOf((long) singlePlaylist.get("numberOfFollowers")), Long.valueOf(tracks.size()));
            myPlaylist.insertAsNewPlaylist();
            for(int j=0; j< tracks.size(); j++)
            {
                JSONObject jsonSingleTrack= (JSONObject) tracks.get(j);
                JSONObject  jsonMoreInformation=(JSONObject)jsonSingleTrack.get("track");
                Artist artist = Artist.getArtistByName((String)jsonMoreInformation.get("artistName"));
                if (artist==null) {
                    artist = new Artist((String) jsonMoreInformation.get("artistName"));
                    artist.insertAsNewArtist();
                }

                Album album = Album.getAlbumByName(((String)jsonMoreInformation.get("albumName")));
                if (album==null) {
                    album = new Album((String)jsonMoreInformation.get("albumName"), artist);
                    album.insertAsNewAlbum();
                }
                Track title = Track.getTrackByName((String)jsonMoreInformation.get("trackName"));
                if (title==null) {
                    title = new Track((String) jsonMoreInformation.get("trackName"));
                    title.insertAsNewTrack();
                }
                Boolean localTrack = (Boolean) jsonSingleTrack.get("localTrack");
                Integer episode = (Integer) jsonSingleTrack.get("episode");
                String trackURI=(String)jsonMoreInformation.get("trackUri");
                title.setAlbum(album);
                title.setTrackURI(trackURI);
                title.setLocaltrack(localTrack);
                title.setEpisode(episode);
                title.updateTrackInfo();

                Date addedDate = null;
                try {
                    addedDate = parseFormat.parse((String) jsonSingleTrack.get("addedDate"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }

                PlaylistTrack singleTrack= new PlaylistTrack(Track.getTrackByName(title.getTrackName()), Playlist.getPlaylistByName(myPlaylist.getName()), addedDate);
                singleTrack.insertAsNewPlaylistTrack();;
            }
        }

    }
}
