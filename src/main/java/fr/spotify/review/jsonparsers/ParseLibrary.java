package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.Track;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.services.AlbumService;
import fr.spotify.review.services.ArtistService;
import fr.spotify.review.services.TrackService;
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
import java.util.List;

@Service
@AllArgsConstructor
public class ParseLibrary {

    private final TrackService trackService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    // @Async("StatifyExecutor")
    public void parseLibrary(MultipartFile file) {
        JSONArray tracks = null, albums = null, episodes = null, shows = null;
        JSONObject library = null;
        JSONTokener playlist = null;
        //Open the File

        if (!file.isEmpty()) {
            try {
                playlist = new JSONTokener(new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            library = new JSONObject(playlist);
            tracks = library.getJSONArray("tracks");
            albums = library.getJSONArray("albums");
            episodes = library.getJSONArray("episodes");
            shows = library.getJSONArray("shows");
            for (int i = 0; i < tracks.length(); i++) {
                JSONObject jsonObj = (JSONObject) tracks.get(i);
                String artistName= jsonObj.getString("artist");
                String albumName= jsonObj.getString("album");
                String trackName= jsonObj.getString("track");
                String trackURI=jsonObj.getString("uri");
                Artist artist=null;
                Album album=null;
                Track title=null;
                if(artistService.existsByName(artistName))
                    artist = artistService.findByName(artistName).get();

                else {
                    artist = new Artist(artistName);
                    artistService.save(artist);
                }

                if(albumService.existsByName(albumName)) album= albumService.findByName(albumName).get();

                else
                {
                    album = new Album(albumName, artist);
                    albumService.save(album);
                }
                if(trackService.existsByTrackURI(trackURI))  {title = trackService.findByTrackURI(trackURI).get();}
                else if (trackService.existsByNameAndAlbumAndAlbumArtist(trackName, album, artist)) {title = trackService.findByNameAndAlbumAndAlbumArtist(trackName, album, artist).get();}
                else if (trackService.existsByNameAndAlbum(trackName, album)) {title = trackService.findByNameAndAlbum(trackName, album).get();}

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
                    else if (titles.isEmpty() || title == null) {
                        title = new Track(trackName, trackURI, album);
                        trackService.save(title);
                    }
                    else title = titles.get(0);
                }
                title.setTrackURI(trackURI);
                trackService.save(title);
            }
        }
    }
}
