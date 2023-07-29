package fr.spotify.review.controllers;

import fr.spotify.review.entities.PlaylistTrack;
import fr.spotify.review.services.PlaylistTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PlaylistTrack>> getPlaylistTrackList() {
        return ResponseEntity.ok(this.playlistTrackService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<PlaylistTrack>> getPlaylistTrackById(Long id) {
        return ResponseEntity.ok(this.playlistTrackService.getPlaylistTrackById(id));
    }
}
