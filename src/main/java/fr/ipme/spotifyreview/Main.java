package fr.ipme.spotifyreview;

import fr.ipme.spotifyreview.database.DatabaseConnect;
import fr.ipme.spotifyreview.jsonparsers.ParsePlaylists;
import fr.ipme.spotifyreview.jsonparsers.ParseStreamingHistory;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ParseStreamingHistory.parseStreamingHistory(); // TESTED. WORKS !
        DatabaseConnect.databaseConnection(); //TESTED. WORKS !
        ParsePlaylists.parsePlaylists();
//        DatabaseConnect.instantiatePMF();
    }
}