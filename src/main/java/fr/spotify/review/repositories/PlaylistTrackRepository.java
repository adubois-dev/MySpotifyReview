package fr.spotify.review.repositories;

import fr.spotify.review.entities.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long> {
}
