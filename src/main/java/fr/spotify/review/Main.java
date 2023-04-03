package fr.spotify.review;

import fr.spotify.review.database.DatabaseConnect;
import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.User;
import fr.spotify.review.jsonparsers.ParseHistorics;
import fr.spotify.review.jsonparsers.ParsePlaylists;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.spotify.review.jsonparsers.ParseUserInfos;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;


public class Main {

    public static final Logger LOGGER = LogManager.getRootLogger();
    public static final Connection CONNECTION = DatabaseConnect.databaseConnection();
//    static final String path = "src/resources/log4j.properties";

    public static void main(String[] args) throws SQLException {

        CONNECTION.setAutoCommit(true);
//        PropertyConfigurator.configure(path);
        LOGGER.setLevel(Level.DEBUG);


//        User user= ParseUserInfos.parseUserInfos();
//        user.insertAsNewUserFromJSON();
//        User.getUserHistorics("adubois.personnel@gmail.com");
//
  //      ArrayList<Historics> histos= ParseHistorics.parseHistorics();
        ParsePlaylists.parsePlaylists();
/*
            for(int i=0;  i<histos.size(); i++)
            {
                histos.get(i).insertAsNewHisto();
            }
*/


        //   ParsePlaylists.parsePlaylists();
//        DatabaseConnect.instantiatePMF();
//        new AlbumDAO().getAllAlbums();

        CONNECTION.close();
    }
}