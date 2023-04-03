package fr.spotify.review.restservice;

import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.OutputAllHistorics;
import fr.spotify.review.domain.User;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HistoricsInfosController {


    @GetMapping("/history")
    public HistoricsInfos historicsInfos(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) throws SQLException {
        return new HistoricsInfos(OutputAllHistorics.getUserHistorics(email));
    }
}