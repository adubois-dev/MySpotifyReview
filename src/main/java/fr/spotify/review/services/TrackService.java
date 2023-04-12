package fr.spotify.review.services;


import fr.spotify.review.entities.Track;
import fr.spotify.review.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getAll() {
        return this.trackRepository.findAll();
    }

    public Optional<Track> getById(Long id) {
        return this.trackRepository.findById(id);
    }

    public Track save(Track track) {return this.trackRepository.save(track);}

    public Track findByTrackURI(String trackURI) {
        return this.trackRepository.findByTrackURI(trackURI);
    }

    public boolean existsByTrackURI(String trackURI) { return this.trackRepository.existsByTrackURI(trackURI);}

    public boolean existsByName(String name) {return this.trackRepository.existsByName(name);}

    public Track findByName(String name) {return this.trackRepository.findByName(name);}
}
