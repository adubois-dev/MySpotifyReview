package fr.spotify.review.repositories;

import fr.spotify.review.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByNameContainsIgnoreCase(String name);

    Optional<Artist> findByName(String name);

    boolean existsByName(String name);
    boolean existsById(Long id);
    Optional<Artist> findById(Long id);
}
