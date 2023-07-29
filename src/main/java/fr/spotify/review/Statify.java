package fr.spotify.review;

import fr.spotify.review.services.FilesStorageService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Statify implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Statify.class);
    @Resource
    FilesStorageService storageService;
    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication();
        sa.run(Statify.class);
    }


    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }

    @Bean
    public static CommandLineRunner application() {
        return (args) -> {

 /*               ParseSpotifyUserInfos.parseSpotifyUserInfos(spuService);
                ParseHistorics.parseHistorics(spuService, histoServ,artistServ, albumServ, trackServ);
                ParseLibrary.parseLibrary(trackServ, albumServ, artistServ);
                ParsePlaylists.parsePlaylists(playTrackServ, playlistServ, spuService, albumServ, artistServ, trackServ);*/
        };
    }

}

