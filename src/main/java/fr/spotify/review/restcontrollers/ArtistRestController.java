package fr.spotify.review.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.services.ArtistService;
import fr.spotify.review.views.Views;
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
    @JsonView(Views.AlbumResponseView.class)
    public List<Artist> getArtistList() {
        return this.artistService.getAll();
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public Optional<Artist> getArtist(@PathVariable Long id) {
        Optional<Artist> artist = this.artistService.getById(id);

        return artist;
    }

}
