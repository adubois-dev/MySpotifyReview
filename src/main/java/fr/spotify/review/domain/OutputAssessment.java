package fr.spotify.review.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.spotify.review.Main.CONNECTION;

public class OutputAssessment {

    public Double nbScrobbles;
    public Double totalTimePlayed;

    public Integer nbArtists;
    public Integer nbTracks;



    public OutputAssessment() {

    }


    public static OutputAssessment getMostPlayed(String email) throws SQLException {
        OutputAssessment returnInstance = new OutputAssessment();
        Statement statement = CONNECTION.createStatement();
        User user = User.getUserByEmail(email);
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS NbScrobbles, SUM(historics.ms_played) AS totalTimePlayed FROM historics WHERE historics.user_id=" + user.getId() + ";");
        rs.next();
        returnInstance.nbScrobbles=rs.getDouble("NbScrobbles");
        returnInstance.totalTimePlayed=rs.getDouble("totalTimePlayed")/60000;
        rs = statement.executeQuery("SELECT COUNT(DISTINCT(artist_id)) AS NbArtists, COUNT(DISTINCT(track_id)) AS NbTracks FROM `historics` where historics.user_id=" + user.getId() + ";");
        rs.next();
        returnInstance.nbArtists=rs.getInt("NbArtists");
        returnInstance.nbTracks=rs.getInt("NbTracks");
        return returnInstance;

    }
}
