package fr.spotify.review.restservice;

import fr.spotify.review.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Error404Controller {

    //private static final String template = "Oops, something went wrong !!! Developers missed some coffees !";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/error")
    public Error404 error404() {

        return new Error404(counter.incrementAndGet(), "Oops, something went wrong !!! Developers missed some coffees !");
    }
}