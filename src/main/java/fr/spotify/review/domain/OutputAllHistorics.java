package fr.spotify.review.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.conn;

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

    public static ArrayList<OutputAllHistorics> getUserHistorics(String email) throws SQLException {
        ArrayList<OutputAllHistorics> returnInstance = new ArrayList<OutputAllHistorics>();
        User user = User.getUserByEmail(email);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT historics.ms_played AS msPlayed, historics.end_time AS datePlayed, historics.user_id AS userId, albums.name AS albumName, artists.name AS Artist, tracks.name AS TrackName FROM historics INNER JOIN tracks ON historics.track_id = tracks.id INNER JOIN albums ON tracks.album_id=albums.id INNER JOIN artists ON artists.id=albums.artist_id ORDER BY datePlayed ASC;");
        while(rs.next())
        {
            if(rs.getInt("userId")==user.getId())
                returnInstance.add(new OutputAllHistorics( rs.getDouble("msPlayed")/60000,rs.getDate("datePlayed"),rs.getString("albumName"), rs.getString("Artist"), rs.getString("TrackName")));
        }
        return returnInstance;
    }

}