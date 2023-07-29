package fr.spotify.review.repositories;

import fr.spotify.review.entities.PlaylistTrack;
import fr.spotify.review.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long> {


    Boolean existsByTrackIdAndPlaylistId(Long track_id, Long playlist_id);
}
