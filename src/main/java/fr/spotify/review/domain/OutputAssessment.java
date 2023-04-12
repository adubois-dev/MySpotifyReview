package fr.spotify.review.domain;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static fr.spotify.review.Main.CONNECTION;

public class OutputAssessment {

    public Double nbScrobbles;
    public Double totalTimePlayed;

    public Integer nbArtists;
    public Integer nbTracks;



    public OutputAssessment() {

    }


    public static OutputAssessment getAssessment(SpotifyUserService spuServ, String email) throws SQLException {
        OutputAssessment returnInstance = new OutputAssessment();
        Statement statement = CONNECTION.createStatement();
        SpotifyUser spUser = spuServ.findSpotifyUserByEmail(email);
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS NbScrobbles, SUM(historics.ms_played) AS totalTimePlayed FROM historics WHERE historics.spotify_user_id=" + spUser.getId() + ";");
        rs.next();
        returnInstance.nbScrobbles=rs.getDouble("NbScrobbles");
        returnInstance.totalTimePlayed=rs.getDouble("totalTimePlayed")/60000;
        rs = statement.executeQuery("SELECT COUNT(DISTINCT(artist_id)) AS NbArtists, COUNT(DISTINCT(track_id)) AS NbTracks FROM `historics` where historics.spotify_user_id=" + spUser.getId() + ";");
        rs.next();
        returnInstance.nbArtists=rs.getInt("NbArtists");
        returnInstance.nbTracks=rs.getInt("NbTracks");
        return returnInstance;

    }
}
