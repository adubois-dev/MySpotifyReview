package fr.spotify.review.jsonparsers;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static fr.spotify.review.Main.LOGGER;

public class ParseHistorics {



    public static void parseHistorics(SpotifyUserService spUserServ, HistoricService historics, ArtistService artists, AlbumService albums, TrackService tracks){

        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        //Open the Files
        JSONTokener jsonTokener = null;
        for (int i = 0; i < 5; i++) {
            try {
                jsonTokener = new JSONTokener((new FileReader("/mnt/docker/MyData/endsong_" + i + ".json")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (jsonTokener != null) {
                //Parse it
                JSONArray jsonArray = new JSONArray(jsonTokener);
                int len = jsonArray.length();
                for (int j = 0; j < len; j++) {
                    JSONObject jsonO = (JSONObject) jsonArray.get(j);
                    Artist artist = null;
                    Album album = null;
                    Track track = null;
                    Double msplayed = null;
                    //Checks performed to see if it is a musical scrobble
                    if (!(jsonO.get("master_metadata_album_artist_name").equals(JSONObject.NULL))) {
                        String artistName = jsonO.getString("master_metadata_album_artist_name");
                        if (artists.existsByName(artistName)) {
                            artist = artists.findByName(artistName);
                        } else {
                            artist = new Artist(artistName);
                            artists.save(artist);
                        }
                    }
                    if (!(jsonO.get("master_metadata_album_album_name").equals(JSONObject.NULL))) {
                        String albumName = jsonO.getString("master_metadata_album_album_name");
                        if (albums.existsByName(albumName)) {
                            album = albums.findByName(albumName);
                        } else {

                            album = new Album(albumName, artist);
                            albums.save(album);
                        }
                    }
                    if (!(jsonO.get("master_metadata_track_name").equals(JSONObject.NULL)) && !(jsonO.get("spotify_track_uri").equals(JSONObject.NULL))) {
                        String trackName = jsonO.getString("master_metadata_track_name");
                        String trackURI = jsonO.getString("spotify_track_uri");
                        if (tracks.existsByTrackURI(trackURI)) {
                            track = tracks.findByTrackURI(trackURI);
                        } else {
                            track=new Track(trackName, trackURI, album);
                            tracks.save(track);
                        }
                    }

                    msplayed = jsonO.getDouble("ms_played");

                    DateTimeFormatter formatEntree = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                    LocalDateTime dateTime = LocalDateTime.parse(jsonO.getString("ts"), formatEntree);
                    Historic histo = new Historic(album, artist, track, spUserServ.findByUserName(jsonO.getString("username")), msplayed, dateTime);
                    historics.save(histo);
                    //LOGGER.debug(histo.toString());
                }
            }
        }
    }
 /*   public static ArrayList<Historic> parseHistoricOldVersion(SpotifyUserService spuService, ArtistService artistServ, HistoricService histoServ, ArtistService artistService, TrackService trackService) throws SQLException {

        LOGGER.debug("Delete all Historic;");
        SpotifyUser spUser = spuService.findSpotifyUserByEmail("adubois.personnel@gmail.com");
        LOGGER.debug("PARSE Historic;");
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        ArrayList<Historic> historyList = new ArrayList<Historic>();
        //Open the Files
        JSONTokener jsonTokener=null;
        for(int i=0;i<4;i++) {
            try {
                jsonTokener = new JSONTokener((new FileReader("/mnt/docker/MyData/StreamingHistory" + i + ".json")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(jsonTokener!=null){
                //Parse it
                JSONArray jsonArray=new JSONArray(jsonTokener);
                int len = jsonArray.length();
                for (int j = 0; j < len; j++) {
                    JSONObject jsonO = (JSONObject) jsonArray.get(j);
                    String trackName=jsonO.getString("trackName");
                    String artistName=jsonO.getString("artistName");
                    Artist artist=null;
                    Track title = null;
                    if(!artistService.existsByName(artistName)){
                        artist = new Artist(artistName);
                        artistService.save(artist);
                    }
                    else artist=artistService.findByName(artistName);
                    if(!trackService.existsByName(trackName)){
                        title = new Track(trackName);
                        trackService.save(title);
                    }
                    else title=trackService.findByName(trackName);
                    Double msplayed = jsonO.getDouble("msPlayed");
                    // parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    LocalDateTime date = null;
                    DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm").toFormatter(Locale.ENGLISH);
                    date = date.parse(jsonO.getString("endTime"), df);
                    LocalDateTime listeningDate = date;
                    Historic histo = new Historic(artist, title, spUser, msplayed, listeningDate);
//            historyList.add(histo);
                    //LOGGER.debug(histo.toString());
                    histoServ.save(histo);
                }
            }
        }
        return historyList;
    }*/
}