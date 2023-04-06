package fr.spotify.review.restservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Statify {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Statify.class);
        // ... customize application settings here
        application.run(args);
    }

}
