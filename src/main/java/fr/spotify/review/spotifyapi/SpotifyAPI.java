package fr.spotify.review.spotifyapi;

import org.json.JSONObject;
import se.michaelthelin.spotify.SpotifyApi;

import java.net.URI;

public class SpotifyAPI {

    public static void spotifyQuery(){
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId("1faf3b88a6233c43787a3495545227f93dd")
                .setClientSecret("e3d875b3c5580546c62a0219184df66713bf")
                .setRedirectUri(URI.create("http://localhost:12345/callback"))
                .build();


    }
}
