package fr.spotify.review.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ErrorController {

    //private static final String template = "Oops, something went wrong !!! Developers missed some coffees !";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/error")
    public Error error() {

        return new Error(counter.incrementAndGet(), "Oops, something went wrong !!! Developers missed some coffees !");
    }
}