package fr.spotify.review.entities.searcharray;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;

import java.util.Collection;

public final class AlbumUtils {
    public static Album findByName(Collection<Album> listAlbums, String name) {
        return FindUtils.findByProperty(listAlbums, album -> name.equals(album.getName()));
    }

    public static Album findById(Collection<Album> listAlbums, Long id) {
        return FindUtils.findByProperty(listAlbums, album -> id.equals(album.getId()));
    }
    public static Album findByArtist(Collection<Album> listAlbums, Artist artist) {
        return FindUtils.findByProperty(listAlbums, album -> artist.equals(album.getArtist()));
    }

}
