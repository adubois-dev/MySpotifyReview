package fr.spotify.review.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.conn;

public class OutputMostPlayed {

public Integer nbTimesPlayed;

    public OutputMostPlayed(Integer nbTimesPlayed, Double totalTimePlayed, String albumName, String artistName, String trackName) {
        this.nbTimesPlayed = nbTimesPlayed;
        this.totalTimePlayed = totalTimePlayed;
        this.albumName = albumName;
        this.artistName = artistName;
        this.trackName = trackName;
    }

    public Double totalTimePlayed;
public String albumName, artistName, trackName;

    public static ArrayList<OutputMostPlayed> getMostPlayed(String email) throws SQLException {
        ArrayList<OutputMostPlayed> returnInstance = new ArrayList<OutputMostPlayed>();
        User user = User.getUserByEmail(email);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT historics.user_id AS userId, COUNT(historics.ms_played) AS NbTimesPlayed, SUM(historics.ms_played) AS totalTimePLayed, albums.name AS albumName, artists.name AS artistName, tracks.name AS trackName FROM historics INNER JOIN tracks ON historics.track_id = tracks.id INNER JOIN albums ON tracks.album_id=albums.id INNER JOIN artists ON artists.id=albums.artist_id GROUP BY tracks.name ORDER BY NbTimesPlayed DESC;");
        while(rs.next())
        {
            if(rs.getInt("userId")==user.getId())
                returnInstance.add(new OutputMostPlayed( rs.getInt("NbTimesPlayed"),rs.getDouble("totalTimePlayed")/60000, rs.getString("albumName"), rs.getString("artistName"), rs.getString("trackName")));
        }
        return returnInstance;

    }
}
