package fr.spotify.review.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Track;
import fr.spotify.review.services.TrackService;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/track")
public class TrackRestController {

    private final TrackService trackService;

    @Autowired
    public TrackRestController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<List<Track>> getTrackList() {
        return ResponseEntity.ok(this.trackService.getAll());
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public ResponseEntity<Optional<Track>> getTrack(@PathVariable Long id) {
        Optional<Track> track = this.trackService.getById(id);
/*        if (track.isEmpty()) {
            throw new TrackNotFoundException(id);
        }*/
        return ResponseEntity.ok(track);
    }

}
