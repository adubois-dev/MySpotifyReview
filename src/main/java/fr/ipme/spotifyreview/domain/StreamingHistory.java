package fr.ipme.spotifyreview.domain;
//import javax.persistence.*;
//
//@Entity
//@Table(name = "user_song")
public class StreamingHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Song song;

    public Long msplayed;
    public ListeningDate listeningDate;
    public StreamingHistory(Song song, Long msplayed, ListeningDate listeningDate) {
        this.song=song;
        this.msplayed=msplayed;
        this.listeningDate=listeningDate;
    }

    @Override
    public String toString() {
        return "StreamingHistory{" +
                "artist=   " + song.getArtist().toString()+
                ", songTitle=    " + song.getSongTitle().toString() +
                ", msplayed=    " + msplayed.toString() +
                ", listeningDate     =" + listeningDate.toString() +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Long getMsplayed() {
        return msplayed;
    }

    public void setMsplayed(Long msplayed) {
        this.msplayed = msplayed;
    }

    public ListeningDate getListeningDate() {
        return listeningDate;
    }

    public void setListeningDate(ListeningDate listeningDate) {
        this.listeningDate = listeningDate;
    }
}
