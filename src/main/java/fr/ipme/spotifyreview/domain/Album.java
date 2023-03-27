package fr.ipme.spotifyreview.domain;


//import javax.persistence.*;
//@Entity
//@Table(name = "album")
public class Album {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String albumName;
    public Album(String albumName) {
        this.albumName=albumName;
    }

    public Album() {

    }

    @Override
    public String toString() {
        return "Album{" +
                "albumName='" + albumName + '\'' +
                '}';
    }

    public String getAlbumName() {
        return albumName;
    }

    public Integer getId() {
    return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
