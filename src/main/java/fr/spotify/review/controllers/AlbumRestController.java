package fr.spotify.review.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Album;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.services.AlbumService;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/album")
public class AlbumRestController {

    private final AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<List<Album>> getAlbumList() {
        List<Album> album = this.albumService.findAll();
        if(album.isEmpty()){
            throw new StatifyResourceNotFoundException("Query returned empty result");
        }
        return ResponseEntity.ok(album);
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = this.albumService.findById(id)
                .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No Album for id : " + id));
        return ResponseEntity.ok(album);
    }

}

