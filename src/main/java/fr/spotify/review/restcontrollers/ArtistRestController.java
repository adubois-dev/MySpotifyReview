package fr.spotify.review.restcontrollers;

import fr.spotify.review.entities.Artist;
import fr.spotify.review.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/artist")
public class ArtistRestController {

    private final ArtistService artistService;

    @Autowired
    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> getArtistList() {
        return this.artistService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Artist> getArtist(@PathVariable Long id) {
        Optional<Artist> artist = this.artistService.getById(id);

        return artist;
    }

}
