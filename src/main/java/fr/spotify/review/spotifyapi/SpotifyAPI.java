package fr.spotify.review.spotifyapi;

import org.json.JSONObject;
import se.michaelthelin.spotify.SpotifyApi;

import java.net.URI;

public class 
SpotifyAPI {

    public static void spotifyQuery(){
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId("1faf3b8a623c4387a3495545227f93dd")
                .setClientSecret("e3d85b3c55054662a021918df66713bf")
                .setRedirectUri(URI.create("http://localhost:12345/callback"))
                .build();


    }
}
