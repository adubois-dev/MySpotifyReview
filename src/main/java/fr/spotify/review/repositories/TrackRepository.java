package fr.spotify.review.repositories;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findAllByNameContains(String name);

    Optional<Track> findByTrackURI(String trackURI);

    Track findByName(String name);
    Boolean existsByTrackURI(String trackURI);

    public Optional<Track> findByNameAndAlbumAndAlbumArtist(String name, Album album, Artist artist);
    public Optional<Track> findByNameAndAlbum(String name, Album album);
   // public Optional<Track> findByNameAndArtistName(String name, String artistName);

    Boolean existsByName(String name);

    List<Track> findAllByName(String trackName);

    Boolean existsByNameAndAlbumAndAlbumArtist(String name, Album album, Artist artist);

    Boolean existsByNameAndAlbum(String trackName, Album album);

   // Boolean existsByNameAndArtistName(String name, String artistName);
}
