package fr.spotify.review.controllers;

import fr.spotify.review.database.DataSourceInit;
import fr.spotify.review.jsonparsers.ParseSpotifyUserInfos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class InitDatabaseController {

/*
    @GetMapping("/initDatabase")
    public InitDatabase initDatabase(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
//        DataSourceInit.getDataSource();
//        SpotifyUser spUser=ParseSpotifyUserInfos.parseSpotifyUserInfos();
//        spUser.insertAsNewSpotifyUserFromJSON();
//        ParseHistorics.parseHistorics();
//        ParsePlaylists.parsePlaylists();
        return new InitDatabase("Success");

    }
*/}