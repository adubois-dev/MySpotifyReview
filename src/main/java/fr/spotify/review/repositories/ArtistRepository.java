package fr.spotify.review.repositories;

import fr.spotify.review.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByNameContains(String name);

    Artist findByName(String name);

    boolean existsByName(String name);

}
