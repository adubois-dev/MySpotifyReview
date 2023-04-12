package fr.spotify.review.controllers;

import fr.spotify.review.domain.OutputMostPlayed;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UserMostPlayedController {


    @GetMapping("/histoplayed")
    public UserMostPlayed spUserMostPlayed(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
         return  null;//new UserMostPlayed(OutputMostPlayed.getMostPlayed(email));
    }
}