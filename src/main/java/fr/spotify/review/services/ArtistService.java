package fr.spotify.review.services;


import fr.spotify.review.entities.Artist;
import fr.spotify.review.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAll() {
        return this.artistRepository.findAll();
    }

    public Optional<Artist> getById(Long id) {
        return this.artistRepository.findById(id);
    }

    public Artist save(Artist artist) { return this.artistRepository.save(artist); }

    public Artist findByName(String name) {
        return this.artistRepository.findByName(name);
    }

    public boolean existsByName(String name) { return this.artistRepository.existsByName(name);}
}
