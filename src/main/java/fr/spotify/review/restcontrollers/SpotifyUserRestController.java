package fr.spotify.review.restcontrollers;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/spUser")
public class SpotifyUserRestController {

    private final SpotifyUserService spotifyUserService;

    @Autowired
    public SpotifyUserRestController(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @GetMapping
    public List<SpotifyUser> getSpotifyUserList() {
        return this.spotifyUserService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<SpotifyUser> getSpotifyUser(@PathVariable Long id) {
        Optional<SpotifyUser> SpotifyUser = this.spotifyUserService.getById(id);

        return SpotifyUser;
    }

}
