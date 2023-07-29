package fr.spotify.review.entities.searcharray;

import fr.spotify.review.entities.Artist;

import java.util.Collection;

public final class ArtistUtils {
    public static Artist findByName(Collection<Artist> listArtists, String name) {
        return FindUtils.findByProperty(listArtists, artist -> name.equals(artist.getName()));
    }

    public static Artist findById(Collection<Artist> listArtists, Long id) {
        return FindUtils.findByProperty(listArtists, artist -> id.equals(artist.getId()));
    }

}
