package fr.ipme.spotifyreview.domain;


//import javax.persistence.*;
//@Entity
//@Table(name = "artist")
public class Artist {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String artistName;
    public Artist(String artistName) {
        this.artistName=artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }
}
