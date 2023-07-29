package fr.spotify.review.services;


import fr.spotify.review.entities.Artist;
import fr.spotify.review.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistService {

    @Autowired
    private final ArtistRepository artistRepository;


    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }
    public List<Artist> findByNameContains(String name) {
        return this.artistRepository.findByNameContainsIgnoreCase(name);
    }

    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    public Artist save(Artist artist) { return this.artistRepository.save(artist); }

    public Optional<Artist> findByName(String name) {
        return this.artistRepository.findByName(name);
    }

    public boolean existsByName(String name) { return this.artistRepository.existsByName(name);}

    public void deleteById(Long id) {this.artistRepository.deleteById(id);}
    public void deleteAll() {this.artistRepository.deleteAll();}

    public boolean existsById(Long id) {return this.artistRepository.existsById(id);
    }
}
