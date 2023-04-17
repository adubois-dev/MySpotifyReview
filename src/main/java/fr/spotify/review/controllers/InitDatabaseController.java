package fr.spotify.review.controllers;

import fr.spotify.review.database.DataSourceInit;
import fr.spotify.review.jsonparsers.ParseHistorics;
import fr.spotify.review.jsonparsers.ParseLibrary;
import fr.spotify.review.jsonparsers.ParsePlaylists;
import fr.spotify.review.jsonparsers.ParseSpotifyUserInfos;
import fr.spotify.review.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class InitDatabaseController {


    public PlaylistTrackService playTrackServ;
    public PlaylistService playlistServ;
    public SpotifyUserService spuService;
    public AlbumService albumServ;
    public HistoricService histoServ;
    public ArtistService artistServ;
    public TrackService trackServ;

    @Autowired
    public InitDatabaseController(PlaylistTrackService playTrackServ, PlaylistService playlistServ, SpotifyUserService spuService, AlbumService albumServ, HistoricService histoServ, ArtistService artistServ, TrackService trackServ) {
        this.playTrackServ = playTrackServ;
        this.playlistServ = playlistServ;
        this.spuService = spuService;
        this.albumServ = albumServ;
        this.histoServ = histoServ;
        this.artistServ = artistServ;
        this.trackServ = trackServ;
    }

    @RequestMapping("/initDatabase")
    public ModelAndView initDatabase(@RequestParam(name="token", value ="", defaultValue ="", required=true)String token) {
        ModelAndView modelAndView = new ModelAndView();
        if(token.equals("KGKT7576I69O7LKJHJKH345798UYTFRG6545RDE456TFGY520OMP")) {
            modelAndView.setViewName("Loading.html");
            ParseSpotifyUserInfos.parseSpotifyUserInfos(spuService);
            ParseHistorics.parseHistorics(spuService, histoServ, artistServ, albumServ, trackServ);
            try {
                ParseLibrary.parseLibrary(trackServ, albumServ, artistServ);
                ParsePlaylists.parsePlaylists(playTrackServ, playlistServ, spuService, albumServ, artistServ, trackServ);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else modelAndView.setViewName("NotGoodToken.html");
        return modelAndView;
    }
}