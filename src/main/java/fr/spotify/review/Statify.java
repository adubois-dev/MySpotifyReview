package fr.spotify.review;

import fr.spotify.review.jsonparsers.*;
import fr.spotify.review.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class Statify {



        private static final Logger log = LoggerFactory.getLogger(Statify.class);

        public static void main(String[] args) {
            SpringApplication.run(Statify.class);
        }

/*        @Bean
        public CommandLineRunner application(PlaylistTrackService playTrackServ, PlaylistService playlistServ, SpotifyUserService spuService, AlbumService albumServ, HistoricService histoServ, ArtistService artistServ, TrackService trackServ) {
            return (args) -> {

                ParseSpotifyUserInfos.parseSpotifyUserInfos(spuService);
                ParseHistorics.parseHistorics(spuService, histoServ,artistServ, albumServ, trackServ);
                ParseLibrary.parseLibrary(trackServ, albumServ, artistServ);
                ParsePlaylists.parsePlaylists(playTrackServ, playlistServ, spuService, albumServ, artistServ, trackServ);
            };
        }*/

    }

