package fr.spotify.review.services;

import fr.spotify.review.entities.Playlist;
import fr.spotify.review.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getAll() {
        return this.playlistRepository.findAll();
    }

    public Optional<Playlist> getPlaylistById(Long id) {
        return this.playlistRepository.findById(id);
    }

    public Playlist getPlaylistByName(String name) {return this.playlistRepository.findByName(name);}
    public Boolean existsByNameAndUserUuid(String name, UUID uuid) { return this.playlistRepository.existsByNameAndUserUuid(name, uuid);}
    public Optional<Playlist> findByNameAndUserUuid(String name, UUID uuid) { return this.playlistRepository.findByNameAndUserUuid(name, uuid);}

    public Playlist save(Playlist myPlaylist) { return this.playlistRepository.save(myPlaylist);}

}