package fr.ipme.spotifyreview.domain;
//import javax.persistence.*;

//@Entity
//@Table(name = "song")
public class SongTitle {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public String songTitle;
    public SongTitle(String songTitle) {
        this.songTitle=songTitle;
    }

    @Override
    public String toString() {
        return songTitle;
    }
}
