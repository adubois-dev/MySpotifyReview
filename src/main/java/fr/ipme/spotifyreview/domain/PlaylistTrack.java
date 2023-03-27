package fr.ipme.spotifyreview.domain;

//import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "track")
public class PlaylistTrack {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Song song;

    public Album album;
    public String trackURI;

    public Date addedDate;

    public String localTrack;

    @Override
    public String toString() {
        return "PlaylistTrack{" +
                "artist=" + song.getArtist().toString() +
                ", songTitle=" + song.getSongTitle().toString() +
                ", album=" + album.toString() +
                ", trackURI='" + trackURI.toString() + '\'' +
                ", addedDate=" + addedDate.toString() +
                ", localTrack='" + localTrack + '\'' +
                ", episode='" + episode + '\'' +
                '}';


    }
    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTrackURI() {
        return trackURI;
    }

    public void setTrackURI(String trackURI) {
        this.trackURI = trackURI;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getLocalTrack() {
        return localTrack;
    }

    public void setLocalTrack(String localTrack) {
        this.localTrack = localTrack;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String episode;

    public PlaylistTrack(Song song, Album album, String trackURI, Date addedDate, String localTrack, String episode) {
        this.song = song;
        this.album = album;
        this.trackURI = trackURI;
        this.addedDate = addedDate;
        this.localTrack = localTrack;
        this.episode = episode;
    }
}