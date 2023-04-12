package fr.spotify.review.controllers;

import fr.spotify.review.domain.OutputAllHistorics;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class HistoricsInfosController {


/*
    @GetMapping("/history")
    public HistoricsInfos historicsInfos(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
        return new HistoricsInfos(OutputAllHistorics.getSpotifyUserHistorics(email));
    }
*/

}
