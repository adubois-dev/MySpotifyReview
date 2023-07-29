package fr.spotify.review.repositories;

import fr.spotify.review.entities.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findAll();
    List<Album> findByNameContainsIgnoreCase(String name);

    Optional<Album> findById(Long id);

    Optional<Album> findByName(String name);

    boolean existsByName(String name);
}
