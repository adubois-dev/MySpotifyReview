package fr.spotify.review.repositories;

import fr.spotify.review.entities.SpotifyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpotifyUserRepository extends JpaRepository<SpotifyUser, Long> {

    List<SpotifyUser> findAll() ;

    Optional<SpotifyUser> findById(Long id);

    public SpotifyUser findByEmail(String email);
    boolean existsByEmail(String email);

    public SpotifyUser save(SpotifyUser spUser) ;

    SpotifyUser findBySpotifyUsername(String spotify_username);

    SpotifyUser findByUserUuid(UUID uuid);


    SpotifyUser findByUserEmail(String email);
}
