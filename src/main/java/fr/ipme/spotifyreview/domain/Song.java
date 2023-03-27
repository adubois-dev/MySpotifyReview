package fr.ipme.spotifyreview.domain;
//import javax.persistence.*;
//@Entity
//@Table(name = "song")
public class Song {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Artist artist;
    public SongTitle songTitle;

    public Song(Artist artist, SongTitle songTitle) {
        this.artist = artist;
        this.songTitle = songTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public SongTitle getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(SongTitle songTitle) {
        this.songTitle = songTitle;
    }
}
