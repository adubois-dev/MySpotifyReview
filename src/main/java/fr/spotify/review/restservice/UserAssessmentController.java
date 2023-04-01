package fr.spotify.review.restservice;

import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.OutputAssessment;
import fr.spotify.review.domain.OutputMostPlayed;
import fr.spotify.review.domain.User;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import static fr.spotify.review.Main.conn;

@RestController
public class UserAssessmentController {

//    private static final String template = "Hello, %s!";

    @GetMapping("/")
    public UserAssessment userAssessment(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
        return new UserAssessment(OutputAssessment.getMostPlayed(email));
    }
}