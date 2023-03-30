package fr.spotify.review.domain;

//import javax.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;

//@Entity
//@Table(name = "track")
public class PlaylistTrack {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Playlist playlist;
    public Track track;
    public Date addedDate;

    public PlaylistTrack(Track track, Playlist maPL, Date addedDate) {
        this.track = track;
        this.playlist=maPL;
        this.addedDate = addedDate;
    }

    public void insertAsNewPlaylistTrack() throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet r = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM playlist_tracks " +
                "INNER JOIN playlists ON playlist_tracks.playlist_id=playlists.id " +
                "INNER JOIN tracks ON tracks.id=playlist_tracks.track_id " +
                "WHERE playlist_tracks.playlist_id=" + this.playlist.getId() + " AND playlist_tracks.track_id=" + this.track.getId() + ";");
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        log.debug("NbLignes == " + count);
        if (count == 0) {
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO playlist_tracks(playlist_id, track_id, added_date) VALUES (" + this.playlist.getId() + ", " + this.track.getId() +", '" + sm.format(this.getAddedDate()) +"');");
            log.debug("Playlist insérée avec succès");
        } else log.debug("Déjà présente dans la base ! On annule ! :)");
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
