package fr.spotify.review.domain;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;

//import javax.persistence.*;
//
//@Entity
//@Table(name = "user_song")
public class Historics {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Artist artist;
    public Track track;
    public User user;

    public Long msplayed;
    public Date listeningDate;

    public Historics(Integer id, Artist artist, Track track, User user, Long msplayed, Date listeningDate) {
        this.id = id;
        this.artist = artist;
        this.track = track;
        this.user = user;
        this.msplayed = msplayed;
        this.listeningDate = listeningDate;
    }

    public Historics(Artist artist, Track track, User user, Long msplayed, Date listeningDate) {
        this.artist = artist;
        this.track = track;
        this.user = user;
        this.msplayed = msplayed;
        this.listeningDate = listeningDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getMsplayed() {
        return msplayed;
    }

    public void setMsplayed(Long msplayed) {
        this.msplayed = msplayed;
    }

    public Date getListeningDate() {
        return listeningDate;
    }

    public void setListeningDate(Date listeningDate) {
        this.listeningDate = listeningDate;
    }

    @Override
    public String toString() {
        return "Historics" +
                "artist=   " + artist.toString()+
                ", title=    " + track.getTrackName() +
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


    public void insertAsNewHisto() throws SQLException {
        this.getArtist().insertAsNewArtist();
        this.artist = Artist.getArtistByName(artist.getArtistName());
        this.getTrack().insertAsNewTrack();
        this.track= Track.getTrackByName(track.getTrackName());
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO historics(user_id, artist_id, track_id, ms_played, end_time) VALUES (" + this.getUser().getId() + ", " + this.getArtist().getId() + ", " + this.getTrack().getId() + ", " + msplayed + ", '" + sm.format(this.listeningDate) + "');");
            System.out.println("Historique musical inséré avec succès");

    }

    public static void DeleteAllHistos() throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM historics;");
        ResultSet r = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM historics;");
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        System.out.println("NbLignes == " + count);
        if (count == 0) {
            System.out.println("Table réinitialisée avec succès");
        }
        else {
            System.out.println("La table n'a pas été correctement réinitialisée");

        }
        }

}
