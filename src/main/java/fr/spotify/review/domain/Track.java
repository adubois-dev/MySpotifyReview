package fr.spotify.review.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;

public class Track {
    private Integer id;
    public String trackName;
    public Album album;
    public String trackURI;
    public Integer episode;
    public Boolean localtrack;




    public Track(String trackName) {
        this.trackName=trackName;

    }

    public Track(int id, String name) {
        this.id=id;
        this.trackName=name;
    }

    public String getTrackURI() {
        return trackURI;
    }

    public void setTrackURI(String trackURI) {
        this.trackURI = trackURI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getUri() {
        return trackURI;
    }

    public void setUri(String uri) {
        this.trackURI = uri;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }
    public Boolean getLocaltrack() {
        return localtrack;
    }

    public void setLocaltrack(Boolean localtrack) {
        this.localtrack = localtrack;
    }


    public void insertAsNewTrack() throws SQLException {
        Statement statement = conn.createStatement();
        String changedTrackName = this.getTrackName().replace("'"," ");
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM tracks WHERE tracks.name='" + changedTrackName + "';");
        rs.next();
        int count = rs.getInt("recordCount");
        rs.close();
        System.out.println("NbLignes == " + count);
        if (count == 0) {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tracks(name) VALUES ('" + changedTrackName + "');");
            System.out.println("Track insérée avec succès");
        } else System.out.println("Cette track est déjà présente dans la base");
    }

    public void updateTrackInfo() throws SQLException {
        Statement statement = conn.createStatement();
        String changedTrackName = this.getTrackName().replace("'"," ");
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM tracks WHERE tracks.name='" + changedTrackName + "';");
        rs.next();
        int count = rs.getInt("recordCount");
        rs.close();
        System.out.println("NbLignes == " + count);
        if (count == 0) {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tracks(name, album_id, local_track, uri, episode) VALUES ('" + changedTrackName + "', " + this.getAlbum().getId()  + ", " + this.getLocaltrack() + ", '" + this.getUri() + "', " + this.getEpisode() + ");");
            System.out.println("Track insérée avec succès");
        } else
        {
            rs = statement.executeQuery("SELECT * FROM tracks WHERE tracks.name='" + changedTrackName + "';");
            rs.next();
            System.out.println("Cette track est déjà présente dans la base. On la met à jour.");
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE tracks SET album_id=" + this.getAlbum().getId()  + ", localtrack=" + this.getLocaltrack() + ", uri='" + this.getUri() + "', episode=" + this.getEpisode() + " WHERE id=" + rs.getInt("id") + ";");

        }
    }
    public static Track getTrackByName(String trackName) throws SQLException {
        Statement statement = conn.createStatement();
        String changedTrackName = trackName.replace("'"," ");
        ResultSet rs = statement.executeQuery("SELECT * FROM tracks WHERE tracks.name='" + changedTrackName + "';");
        Track track = null;
        if (rs.next()) {
            track = new Track((Integer) rs.getInt("id"), rs.getString("name"));
            System.out.println("found track : Track Name : " + rs.getString("name"));
        }
        return track;
    }

    public static Track getTrackById(Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM tracks WHERE id=" + id + ";");
        Track track = null;
        if (rs.next()) {
            track = new Track((Integer) rs.getInt("id"), rs.getString("name"));
            System.out.println("found track : Track Name : " + rs.getString("name"));
        }
        return track;
    }

}