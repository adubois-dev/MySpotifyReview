package fr.spotify.review.restservice;

import fr.spotify.review.domain.OutputMostPlayed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UserMostPlayedController {


    @GetMapping("/histoplayed")
    public UserMostPlayed userMostPlayed(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
         return  new UserMostPlayed(OutputMostPlayed.getMostPlayed(email));
    }
}