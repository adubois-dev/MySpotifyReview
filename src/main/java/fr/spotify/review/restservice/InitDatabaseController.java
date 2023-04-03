package fr.spotify.review.restservice;

import fr.spotify.review.database.DataSourceInit;
import fr.spotify.review.domain.User;
import fr.spotify.review.jsonparsers.ParseHistorics;
import fr.spotify.review.jsonparsers.ParsePlaylists;
import fr.spotify.review.jsonparsers.ParseUserInfos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class InitDatabaseController {

    @GetMapping("/initDatabase")
    public InitDatabase initDatabase(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
        DataSourceInit.getDataSource();
        User user=ParseUserInfos.parseUserInfos();
        user.insertAsNewUserFromJSON();
        ParseHistorics.parseHistorics();
        ParsePlaylists.parsePlaylists();
        return new InitDatabase("Success");
    }
}