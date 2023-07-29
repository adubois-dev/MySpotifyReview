package fr.spotify.review.services;


import fr.spotify.review.entities.Album;
import fr.spotify.review.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }

    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }

    public List<Album> findByNameContains(String name) {
        return this.albumRepository.findByNameContainsIgnoreCase(name);
    }
    public Optional<Album> findByName(String name) {
        return this.albumRepository.findByName(name);
    }

    public Album save(Album album) { return this.albumRepository.save(album); }

    public Boolean existsByName(String name) {return this.albumRepository.existsByName(name);}
    public Boolean existsById(Long id) {return this.albumRepository.existsById(id);}

    public void deleteAll() { this.albumRepository.deleteAll();}
    public void deleteById(Long id) { this.albumRepository.deleteById(id);}
}
