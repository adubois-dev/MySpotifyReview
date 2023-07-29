package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.*;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.repositories.UserRepository;
import fr.spotify.review.services.*;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import static fr.spotify.review.Main.CONNECTION;

@Service
@AllArgsConstructor
public class ParsePlaylists {
    private final PlaylistTrackService playlistTrackService;
    private final PlaylistService playlistService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final TrackService trackService;

    public void parsePlaylists(User user, MultipartFile file){
        JSONArray playlists = null;
        JSONTokener playlist = null;
        if (!file.isEmpty()) {
            try {
                playlist = new JSONTokener(new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject playlistTemp = new JSONObject(playlist);
            playlists = playlistTemp.getJSONArray("playlists");

            for (int i = 0; i < playlists.length(); i++) {
                JSONObject singlePlaylist = (JSONObject) playlists.get(i);
                Date lastModifiedDate = null;
                DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    lastModifiedDate = parseFormat.parse(singlePlaylist.getString("lastModifiedDate"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }

                JSONArray tracks = singlePlaylist.getJSONArray("items");

                //Case description field is null
                String description;
                if (singlePlaylist.get("description").equals(JSONObject.NULL)) description = "";
                else description = singlePlaylist.getString("description");
                Playlist myPlaylist=null;
                if(playlistService.existsByNameAndUserUuid(singlePlaylist.getString("name"), user.getUuid())) myPlaylist=playlistService.findByNameAndUserUuid(singlePlaylist.getString("name"), user.getUuid())
                        .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No Artist for id : "));
                else {
                    myPlaylist = new Playlist(singlePlaylist.getString("name"), user, lastModifiedDate, description, singlePlaylist.getLong("numberOfFollowers"), Long.valueOf(tracks.length()));
                    playlistService.save(myPlaylist);
                }
                for (int j = 0; j < tracks.length(); j++) {
                    JSONObject jsonSingleTrack = (JSONObject) tracks.get(j);
                    JSONObject jsonMoreInformation = jsonSingleTrack.getJSONObject("track");
                    String trackURI = jsonMoreInformation.getString("trackUri");
                    String artistName=jsonMoreInformation.getString("artistName");
                    Artist artist=null;
                   if(artistService.existsByName(artistName))
                     artist = artistService.findByName(artistName).get();

                    else {
                        artist = new Artist(artistName);
                        artistService.save(artist);
                    }

                    Album album =null;
                    if(albumService.existsByName(jsonMoreInformation.getString("albumName"))) album= albumService.findByName(jsonMoreInformation.getString("albumName")).get();

                    else
                    {
                        album = new Album(jsonMoreInformation.getString("albumName"), artist);
                        albumService.save(album);
                    }
                    Boolean localTrack;
                    String trackName=jsonMoreInformation.getString("trackName");
                    if (jsonSingleTrack.get("localTrack").equals(JSONObject.NULL)) localTrack = false;
                    else localTrack = jsonSingleTrack.getBoolean("localTrack");
                    Boolean episode;
                    if (jsonSingleTrack.get("episode").equals(JSONObject.NULL)) episode = false;
                    else episode = jsonSingleTrack.getBoolean("episode");
                    Track title=null;
                    if(trackService.existsByTrackURI(trackURI))  title = trackService.findByTrackURI(trackURI).get();
                    else if (trackService.existsByNameAndAlbumAndAlbumArtist(trackName, album, artist)) title = trackService.findByNameAndAlbumAndAlbumArtist(trackName, album, artist).get();
                    else if (trackService.existsByNameAndAlbum(trackName, album)) title = trackService.findByNameAndAlbum(trackName, album).get();

                    if(title==null)
                    {
                        List<Track> titles = trackService.findAllByName(trackName);
                        if (titles.size() > 1) {
                            for (Track track : titles) {
                                if (track.getAlbum() != null && track.getAlbum().getArtist() != null && track.getAlbum().getArtist().getName().equals(artist.getName())) {
                                    title = track;
                                }
                            }
                        title= titles.get(0); // NO choice anymore. Have to assign a value.
                        }
                        else if (titles.isEmpty()) {
                            title = new Track(trackName, trackURI, album);
                            title.setLocaltrack(localTrack);
                            title.setEpisode(episode);
                            trackService.save(title);
                        }
                        else title = titles.get(0);
                    }
                    title.setTrackURI(trackURI);
                    title.setLocaltrack(localTrack);
                    title.setEpisode(episode);
                    trackService.save(title);


                    Date addedDate = null;
                    try {
                        addedDate = parseFormat.parse(jsonSingleTrack.getString("addedDate"));
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                   if(!playlistTrackService.existsByTrackIdAndPlaylistId(title.getId(),myPlaylist.getId())) {
                       PlaylistTrack singleTrack = new PlaylistTrack(title, myPlaylist, addedDate);
                       playlistTrackService.save(singleTrack);
                   }
                }
            }
        }
    }
}
