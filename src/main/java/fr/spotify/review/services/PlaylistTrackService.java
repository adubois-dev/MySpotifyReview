
package fr.spotify.review.services;

import fr.spotify.review.entities.PlaylistTrack;
import fr.spotify.review.repositories.PlaylistTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistTrackService {

    private final PlaylistTrackRepository playlistTrackRepository;

    @Autowired
    public PlaylistTrackService(PlaylistTrackRepository playlistTrackRepository) {
        this.playlistTrackRepository = playlistTrackRepository;
    }

    public List<PlaylistTrack> getAll() {
        return this.playlistTrackRepository.findAll();
    }

    public Optional<PlaylistTrack> getPlaylistTrackById(Long id) {
        return this.playlistTrackRepository.findById(id);
    }


    public PlaylistTrack save(PlaylistTrack singleTrack) {return this.playlistTrackRepository.save(singleTrack);}
}
