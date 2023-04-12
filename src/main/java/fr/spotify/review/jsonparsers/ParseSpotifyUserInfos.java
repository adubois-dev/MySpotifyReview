package fr.spotify.review.jsonparsers;

import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.services.SpotifyUserService;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.spotify.review.Main.LOGGER;

public class ParseSpotifyUserInfos {

    public static SpotifyUser parseSpotifyUserInfos(SpotifyUserService spuServ) {
       LOGGER.debug("Parse SpotifyUser Informations;");
        JSONTokener tokener = null;
        JSONObject spUserInfos = null;
       LOGGER.debug("Open the Userdata.json File");
        try {
            tokener=new JSONTokener(new FileReader("/mnt/docker/MyData/Userdata.json"));
            spUserInfos = new JSONObject(tokener);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Date creationDate, birthDate = null;
        DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthDate = parseFormat.parse((String) spUserInfos.get("birthdate"));
            creationDate = parseFormat.parse((String) spUserInfos.get("creationTime"));
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        SpotifyUser spUser=null;
        if(!spuServ.existsByEmail(spUserInfos.getString("email"))) {
            spUser = new SpotifyUser(spUserInfos.getString("email"), spUserInfos.getString("username"), spUserInfos.getString("country"), spUserInfos.getString("gender"), birthDate, creationDate) ;
            spuServ.save(spUser);
        }
    return spUser;
    }
}
