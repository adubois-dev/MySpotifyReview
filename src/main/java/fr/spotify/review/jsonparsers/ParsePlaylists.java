package fr.spotify.review.jsonparsers;

import fr.spotify.review.domain.*;
import fr.spotify.review.entities.*;
import fr.spotify.review.services.*;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.spotify.review.Main.CONNECTION;

public class ParsePlaylists {

    public static void parsePlaylists(PlaylistTrackService playTrackServ, PlaylistService playlistService, SpotifyUserService spuService, AlbumService albumServ, ArtistService artistService, TrackService trackService) throws SQLException {
        //PARSE StreamingHistory;
        JSONArray playlists = null;
        JSONTokener playlist = null;
        //Open the File
        try {
            playlist = new JSONTokener(new FileReader("/mnt/docker/MyData/Playlist1.json"));
            JSONObject playlistTemp= new JSONObject(playlist);
            playlists=playlistTemp.getJSONArray("playlists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Parse i
        int len = playlists.length();

        for (int i = 0; i < len; i++) {
            JSONObject singlePlaylist = (JSONObject) playlists.get(i);
            Date lastModifiedDate = null;
            DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                lastModifiedDate = parseFormat.parse((String) singlePlaylist.getString("lastModifiedDate"));
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }

            JSONArray tracks = singlePlaylist.getJSONArray("items");

            //Case description field is null
            String description;
            if(singlePlaylist.get("description").equals(JSONObject.NULL))description="";
            else description=singlePlaylist.getString("description");

            Playlist myPlaylist = new Playlist((String) singlePlaylist.getString("name"), spuService.findSpotifyUserByEmail("adubois.personnel@gmail.com"), lastModifiedDate, description, singlePlaylist.getLong("numberOfFollowers"), Long.valueOf(tracks.length()));
            playlistService.save(myPlaylist);
            for(int j=0; j< tracks.length(); j++)
            {
                JSONObject jsonSingleTrack= (JSONObject) tracks.get(j);
                JSONObject  jsonMoreInformation=(JSONObject)jsonSingleTrack.getJSONObject("track");
                String trackURI= jsonMoreInformation.getString("trackUri");
                Artist artist = artistService.findByName(jsonMoreInformation.getString("artistName"));
                if (artist==null) {
                    artist = new Artist(jsonMoreInformation.getString("artistName"));
                    artistService.save(artist);
                }

                Album album = albumServ.findByName(jsonMoreInformation.getString("albumName"));
                if (album==null) {
                    album = new Album(jsonMoreInformation.getString("albumName"), artist);
                    albumServ.save(album);
                }
                Track title = trackService.findByTrackURI(trackURI);
                if (title==null) {
                    title = new Track(jsonMoreInformation.getString("trackName"), trackURI, album);
                    trackService.save(title);
                }
                Boolean localTrack;
                if(jsonSingleTrack.get("localTrack").equals(JSONObject.NULL))localTrack=false;
                else localTrack= jsonSingleTrack.getBoolean("localTrack");
                Boolean episode;
                if(jsonSingleTrack.get("episode").equals(JSONObject.NULL))episode=false;
                else episode = jsonSingleTrack.getBoolean("episode");
                title.setTrackURI(trackURI);
                title.setLocaltrack(localTrack);
                title.setEpisode(episode);
                trackService.save(title);

                //title.updateTrackInfo();

                Date addedDate = null;
                try {
                    addedDate = parseFormat.parse(jsonSingleTrack.getString("addedDate"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }

                PlaylistTrack singleTrack= new PlaylistTrack(trackService.findByTrackURI(title.getTrackURI()), playlistService.getPlaylistByName(myPlaylist.getName()), addedDate);
                playTrackServ.save(singleTrack);
              //  singleTrack.insertAsNewPlaylistTrack();
               // CONNECTION.commit();
            }
        }

    }
}
