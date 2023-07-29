package fr.spotify.review.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.services.ArtistService;
import fr.spotify.review.views.Views;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/artist")
@AllArgsConstructor
public class ArtistRestController {

    @Autowired
    private final ArtistService artistService;


    @GetMapping
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<List<Artist>> getArtistList() {
        List<Artist> artist = this.artistService.findAll();
        if(artist.isEmpty()){
            throw new StatifyResourceNotFoundException("Query returned empty result");
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<Artist> getArtist(@PathVariable Long id) {
        Artist artist = this.artistService.findById(id)
                .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No Artist for id : " + id));

        return ResponseEntity.ok(artist);
    }

}
