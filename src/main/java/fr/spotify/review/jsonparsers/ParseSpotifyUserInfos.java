package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.entities.User;
import fr.spotify.review.services.SpotifyUserService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class ParseSpotifyUserInfos {
    private final SpotifyUserService spotifyUserService;

    public SpotifyUser parseSpotifyUserInfos(User user, MultipartFile file)  {
        JSONObject spUserInfos = null;
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        spUserInfos = new JSONObject(tokener);


        Date creationDate, birthDate = null;
        DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthDate = parseFormat.parse((String) spUserInfos.get("birthdate"));
            creationDate = parseFormat.parse((String) spUserInfos.get("creationTime"));
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        SpotifyUser spUser=null;
        if(!this.spotifyUserService.existsByEmail(spUserInfos.getString("email"))) {
            spUser = new SpotifyUser(
                    spUserInfos.getString("email"),
                    spUserInfos.getString("username"),
                    spUserInfos.getString("country"),
                    spUserInfos.getString("gender"),
                    birthDate,
                    creationDate,
                    user
            ) ;
            this.spotifyUserService.save(spUser);
        }
        return spUser;
    }
}
