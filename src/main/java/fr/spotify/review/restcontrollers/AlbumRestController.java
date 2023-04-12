package fr.spotify.review.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Album;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.spotify.review.services.AlbumService;
import java.util.List;
import java.util.Optional;

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
    public List<Album> getAlbumList() {
        return this.albumService.getAll();
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public Optional<Album> getAlbumById(@PathVariable Long id) {
        return albumService.findById(id);
    }




}

