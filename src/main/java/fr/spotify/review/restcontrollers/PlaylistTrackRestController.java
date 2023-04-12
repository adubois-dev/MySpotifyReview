package fr.spotify.review.restcontrollers;

import fr.spotify.review.entities.PlaylistTrack;
import fr.spotify.review.services.PlaylistTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/playlistTrack")
public class PlaylistTrackRestController {

    private final PlaylistTrackService playlistTrackService;

    @Autowired
    public PlaylistTrackRestController(PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    @GetMapping
    public List<PlaylistTrack> getPlaylistTrackList() {
        return this.playlistTrackService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<PlaylistTrack> getPlaylistTrackById(Long id) {
        return this.playlistTrackService.getPlaylistTrackById(id);
    }
}
