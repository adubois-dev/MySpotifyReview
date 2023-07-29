package fr.spotify.review.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.OutputMostPlayed;
import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins={"http://localhost","http://anonomous.fr:8081","http://statifyfront:8081","http://localhost:8082","http://localhost:8081"}, allowCredentials = "true")
@RequestMapping("api/spUser")
public class SpotifyUserRestController {

    private final SpotifyUserService spotifyUserService;

    @Autowired
    public SpotifyUserRestController(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @GetMapping
    public ResponseEntity<List<SpotifyUser>> getSpotifyUserList() {
        return ResponseEntity.ok(this.spotifyUserService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<SpotifyUser>> getSpotifyUser(@PathVariable Long id) {
        Optional<SpotifyUser> SpotifyUser = this.spotifyUserService.getById(id);

        return ResponseEntity.ok(SpotifyUser);
    }
    @GetMapping("/user/{uuid}")
    @JsonView(Views.HistoricsView.class)
    public ResponseEntity<SpotifyUser> getSpotifyUser(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.spotifyUserService.findByUserUuid(uuid));
    }

}
