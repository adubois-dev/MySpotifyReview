package fr.spotify.review.controllers;

import fr.spotify.review.entities.Playlist;
import fr.spotify.review.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/playlist")
public class PlaylistRestController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getPlaylistList() {
        return ResponseEntity.ok(this.playlistService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Playlist>> getPlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }
}
