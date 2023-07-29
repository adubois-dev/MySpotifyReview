package fr.spotify.review.repositories;

import fr.spotify.review.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
     Playlist findByName(String name);
     Boolean existsByNameAndUserUuid(String name, UUID uuid);

     Optional<Playlist> findByNameAndUserUuid(String name, UUID uuid);
}
