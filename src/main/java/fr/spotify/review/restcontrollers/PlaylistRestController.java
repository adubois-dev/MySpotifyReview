package fr.spotify.review.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Playlist;
import fr.spotify.review.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Playlist> getPlaylistList() {
        return this.playlistService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Playlist> getPlaylistById(@PathVariable Long id) {
        return playlistService.getPlaylistById(id);
    }
}
