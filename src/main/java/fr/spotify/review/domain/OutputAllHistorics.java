package fr.spotify.review.domain;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.CONNECTION;

public class OutputAllHistorics {

public Double msPlayed;
public Date date;
public String albumName;
public String artist;
public String trackName;
    public OutputAllHistorics(Double msPlayed, Date date, String albumName, String artist, String trackName) {
    this.msPlayed=msPlayed;
    this.date=date;
    this.albumName=albumName;
    this.artist=artist;
    this.trackName= trackName;
    }

    public static ArrayList<OutputAllHistorics> getSpotifyUserHistorics(SpotifyUserService spuServ, String email) throws SQLException {
        ArrayList<OutputAllHistorics> returnInstance = new ArrayList<OutputAllHistorics>();
        SpotifyUser spUser = spuServ.findSpotifyUserByEmail(email);
        Statement statement = CONNECTION.createStatement();
        ResultSet rs = statement.executeQuery("SELECT historics.ms_played AS msPlayed, historics.end_time AS datePlayed, historics.spUser_id AS spUserId, albums.name AS albumName, artists.name AS Artist, tracks.name AS TrackName FROM historics INNER JOIN tracks ON historics.track_id = tracks.id INNER JOIN albums ON tracks.album_id=albums.id INNER JOIN artists ON artists.id=albums.artist_id WHERE historics.spotify_user_id=" + spUser.getId() + " ORDER BY datePlayed ASC;");
        while(rs.next())
        {
                returnInstance.add(new OutputAllHistorics( rs.getDouble("msPlayed")/60000,rs.getDate("datePlayed"),rs.getString("albumName"), rs.getString("Artist"), rs.getString("TrackName")));
        }
        return returnInstance;
    }

}
