package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.*;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.services.*;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ParseHistorics {

    private final SpotifyUserService spotifyUserService;
    private final ArtistService artistService;
    private final HistoricService historicService;
    private final TrackService trackService;
    private final AlbumService albumService;

    //@Async("StatifyExecutor")
    public void parseHistoricsFull(User spUser, MultipartFile file) {
        //Open the Files
        JSONTokener jsonTokener = null;
        if (!file.isEmpty()) {
            try {
                jsonTokener = new JSONTokener(new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                    if (artistService.existsByName(artistName)) {
                        artist = artistService.findByName(artistName).get();
                    } else {
                        artist = new Artist(artistName);
                        artistService.save(artist);
                    }
                }
                if (!(jsonO.get("master_metadata_album_album_name").equals(JSONObject.NULL))) {
                    String albumName = jsonO.getString("master_metadata_album_album_name");
                    if (albumService.existsByName(albumName)) {
                        album = albumService.findByName(albumName).get();
                    } else {
                        album = new Album(albumName, artist);
                        albumService.save(album);
                    }
                }
                if (        !(jsonO.get("master_metadata_track_name").equals(JSONObject.NULL)) && !(jsonO.get("spotify_track_uri").equals(JSONObject.NULL))) {
                    String trackName = jsonO.getString("master_metadata_track_name");
                    String trackURI = jsonO.getString("spotify_track_uri");
                    if (trackService.existsByTrackURI(trackURI)) {
                        track = trackService.findByTrackURI(trackURI)
                                .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No Track FOund WIth these informations"));
                    } else {
                        track = new Track(trackName, trackURI, album);
                        trackService.save(track);
                    }
                }
                msplayed = jsonO.getDouble("ms_played");
                DateTimeFormatter formatEntree = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                LocalDateTime dateTime = LocalDateTime.parse(jsonO.getString("ts"), formatEntree);
                Historic histo = new Historic(album, artist, track, spUser, msplayed, dateTime);
                if (artist!=null && album != null && track!=null) System.out.println(artist.getName()+ "  album:  " + album.getName() + " Track  :  " + track.getName() + "   Size : i=  "+ j + "  /  " + len);
                if(!historicService.findIfHistoricexists(dateTime, spUser, artist, track)) historicService.save(histo);
            }
        }
    }

    //@Async("StatifyExecutor")
    public void parseHistoricVersion(User user, MultipartFile file) {
        System.out.println(Thread.currentThread().getName());
       JSONTokener jsonTokener = null;

        if (!file.isEmpty()) {
            try {
                jsonTokener = new JSONTokener(new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Parse it
            JSONArray jsonArray = new JSONArray(jsonTokener);
            int len = jsonArray.length();
            for (int j = 0; j < len; j++) {
                JSONObject jsonO = (JSONObject) jsonArray.get(j);
                String trackName = jsonO.getString("trackName");
                String artistName = jsonO.getString("artistName");
                Artist artist = null;
                Track title = null;
                Album album = null;
                List<Track> titles = null;
                if (!artistService.existsByName(artistName)) {
                    artist = new Artist(artistName);
                    artistService.save(artist);
                } else artist = artistService.findByName(artistName).get();
                if (!trackService.existsByName(trackName)) {
                    title = new Track(trackName);
                    trackService.save(title);
                } else {
                    titles = trackService.findAllByName(trackName);
                    if (titles.size() > 1) {
                        for (Track track : titles) {
                            if(track.getAlbum()!=null && track.getAlbum().getArtist()!=null && track.getAlbum().getArtist().getName().equals(artistName)) {
                                album = track.getAlbum();
                                title = track;
                            }
                            if(title==null)title=titles.get(0); //no  choice anymore. have to assign a value
                        }
                    } else title = titles.get(0);
                }
                Double msplayed = jsonO.getDouble("msPlayed");
                LocalDateTime date = null;
                DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm").toFormatter(Locale.ENGLISH);
                date = date.parse(jsonO.getString("endTime"), df);
                LocalDateTime listeningDate = date;
                Historic histo = new Historic(album, artist, title, user, msplayed, listeningDate);
                if (artist!=null && title!=null) System.out.println(artist.getName()+ " Track  :  " + title.getName() + "   Size : i=  "+ j + "  /  " + len);

                if(!historicService.findIfHistoricexists(listeningDate, user, artist, title)) historicService.save(histo);
            }
        }
    }
}
