package fr.spotify.review.repositories;

import fr.spotify.review.entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

@Query(value = "select new fr.spotify.review.entities.OutputMostPlayed(COUNT(h.msplayed), SUM(h.msplayed), h.artist, h.track) FROM Historic h WHERE h.user.uuid=:user_uuid GROUP BY h.track.name, h.artist.name ORDER BY COUNT(h.msplayed) DESC")
    List<OutputMostPlayed> findUserMostPlayed(@Param("user_uuid")UUID uuid, Pageable pageable);
    @Query(value = "select new fr.spotify.review.entities.OutputAssessment (COUNT(*), SUM(h.msplayed), COUNT(DISTINCT(h.artist)), COUNT(DISTINCT(h.track))) FROM Historic h WHERE h.user.uuid=:user_uuid")
    OutputAssessment getUserAssessment(@Param("user_uuid") UUID uuid);

    List<Historic> findAllByUserUuid(UUID id, Pageable pageable);
    Boolean existsByDatePlayedAndUserAndArtistAndTrack(LocalDateTime datePlayed, User user, Artist artist, Track track);
}
