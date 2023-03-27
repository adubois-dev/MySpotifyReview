package fr.ipme.spotifyreview.jsonparsers;

import fr.ipme.spotifyreview.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParsePlaylists {

    public static void parsePlaylists() {
        //PARSE StreamingHistory;
        JSONParser jsonP = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        JSONArray playlists = null;
        JSONObject playlist = null;
        //Open the File
        try {
            playlist = (JSONObject) jsonP.parse(new FileReader("RessourcesExterieures/MyData/Playlist1.json"));
            playlists =(JSONArray) playlist.get("playlists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<StreamingHistory> historyList = new ArrayList<StreamingHistory>();
        //Parse it
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
            Playlist myPlaylist = new Playlist((String) singlePlaylist.get("name"), lastModifiedDate, (String) singlePlaylist.get("description"), Long.valueOf((long) singlePlaylist.get("numberOfFollowers")), Long.valueOf(tracks.size()));
            for(int j=0; j< tracks.size(); j++)
            {
                JSONObject jsonSingleTrack= (JSONObject) tracks.get(j);
                JSONObject  jsonMoreInformation=(JSONObject)jsonSingleTrack.get("track");
                Artist artist=new Artist((String) jsonMoreInformation.get("artistName"));
                Album album=new Album((String) jsonMoreInformation.get("albumName"));
                SongTitle songTitle = new SongTitle((String) jsonMoreInformation.get("trackName"));
                Song song = new Song(artist, songTitle);
                Date addedDate = null;
                try {
                    addedDate = parseFormat.parse((String) jsonSingleTrack.get("addedDate"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }
                PlaylistTrack singleTrack= new PlaylistTrack(song, album, ((String) jsonMoreInformation.get("trackUri")), addedDate, (String)jsonSingleTrack.get("localTrack"), (String)jsonSingleTrack.get("episode"));
                System.out.println(singleTrack.toString());
            }
        }
    }
}
