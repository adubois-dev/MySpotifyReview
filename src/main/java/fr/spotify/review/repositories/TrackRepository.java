package fr.spotify.review.repositories;

import fr.spotify.review.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findAllByNameContains(String name);

    Track findByTrackURI(String trackURI);

    Track findByName(String name);
    boolean existsByTrackURI(String trackURI);

    boolean existsByName(String name);
}
