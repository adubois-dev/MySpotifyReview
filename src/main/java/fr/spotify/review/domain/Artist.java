package fr.spotify.review.domain;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;

//import javax.persistence.*;
//@Entity
//@Table(name = "artist")
public class Artist {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String artistName;

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    public Artist(int id, String artistName) {

        this.id = id;
        this.artistName = artistName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return artistName;
    }

    public void insertAsNewArtist() throws SQLException {

        String changedArtistName = this.getArtistName().replace("'"," ");
        Statement statement = conn.createStatement();
        ResultSet r = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM artists WHERE artists.name='" + changedArtistName + "';");
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        System.out.println("NbLignes == " + count);
        if (count == 0) {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO artists(name) VALUES ('" + changedArtistName + "');");
            System.out.println("Artiste inséré avec succès");
        } else System.out.println("Cet artiste est déjà présent dans la base");
    }

    public static Artist getArtistByName(String artistName) throws SQLException {
        Statement statement = conn.createStatement();
        String changedArtistName = artistName.replace("'"," ");
        ResultSet rs = statement.executeQuery("SELECT * FROM artists WHERE artists.name='" + changedArtistName + "';");
        Artist artist = null;
        if (rs.next()) {
            artist = new Artist((Integer) rs.getInt("id"), rs.getString("name"));
            System.out.println("found artist : Artist Name : " + rs.getString("name"));
        }
        return artist;
    }

    public static Artist getArtistById(Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM artists WHERE id=" + id + ";");
        Artist artist = null;
        if (rs.next()) {
            artist = new Artist((Integer) rs.getInt("id"), rs.getString("name"));
            System.out.println("found artist : Artist Name : " + rs.getString("name"));
        }
        return artist;
    }
}