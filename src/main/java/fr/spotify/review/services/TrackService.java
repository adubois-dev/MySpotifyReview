package fr.spotify.review.services;


import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
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

    public Optional<Track> findByTrackURI(String trackURI) {
        return this.trackRepository.findByTrackURI(trackURI);
    }

    public Optional<Track> findByNameAndAlbumAndAlbumArtist(String name, Album album, Artist artist) {
        return this.trackRepository.findByNameAndAlbumAndAlbumArtist(name, album, artist);
    }
    public Optional<Track> findByNameAndAlbum(String name, Album album) {
        return this.trackRepository.findByNameAndAlbum(name, album);
    }

    public Boolean existsByNameAndAlbumAndAlbumArtist(String name, Album album, Artist artist) {
        return this.trackRepository.existsByNameAndAlbumAndAlbumArtist(name, album, artist);
    }


    public Boolean existsByTrackURI(String trackURI) { return this.trackRepository.existsByTrackURI(trackURI);}

    public Boolean existsByName(String name) {return this.trackRepository.existsByName(name);}

    public Track findByName(String name) {return this.trackRepository.findByName(name);}

    public List<Track> findAllByName(String trackName) {
        return this.trackRepository.findAllByName(trackName);
    }

    public Boolean existsByNameAndAlbum(String trackName, Album album) { return this.trackRepository.existsByNameAndAlbum( trackName,  album);}
}
