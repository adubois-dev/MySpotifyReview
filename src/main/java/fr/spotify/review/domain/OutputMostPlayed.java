package fr.spotify.review.domain;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import static fr.spotify.review.Main.CONNECTION;

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

/*    public static ArrayList<OutputMostPlayed> getMostPlayed(SpotifyUserService spuServ, String email) throws SQLException {
        ArrayList<OutputMostPlayed> returnInstance = new ArrayList<OutputMostPlayed>();
        SpotifyUser spUser = spuServ.findSpotifyUserByEmail(email);
        Statement statement = CONNECTION.createStatement();
        ResultSet rs = statement.executeQuery("SELECT historics.spUser_id AS spUserId, COUNT(historics.ms_played) AS NbTimesPlayed, SUM(historics.ms_played) AS totalTimePLayed, albums.name AS albumName, artists.name AS artistName, tracks.name AS trackName FROM historics INNER JOIN tracks ON historics.track_id = tracks.id INNER JOIN albums ON tracks.album_id=albums.id INNER JOIN artists ON artists.id=albums.artist_id WHERE historics.spotify_user_id=" + spUser.getId() + " GROUP BY tracks.name ORDER BY NbTimesPlayed DESC;");
        while(rs.next())
        {
            if(rs.getInt("spUserId")==spUser.getId())
                returnInstance.add(new OutputMostPlayed( rs.getInt("NbTimesPlayed"),rs.getDouble("totalTimePlayed")/60000, rs.getString("albumName"), rs.getString("artistName"), rs.getString("trackName")));
        }
        return returnInstance;

    }*/
}
