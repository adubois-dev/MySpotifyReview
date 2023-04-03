package fr.spotify.review.jsonparsers;

import fr.spotify.review.domain.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.spotify.review.Main.LOGGER;

public class ParseUserInfos {

    public static User parseUserInfos() {
       LOGGER.debug("Parse User Informations;");
        JSONParser parser = new JSONParser();
        JSONObject userInfos = null;
       LOGGER.debug("Open the Userdata.json File");
        try {
            userInfos = (JSONObject) parser.parse(new FileReader("MyData/Userdata.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date creationDate, birthDate = null;
        DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthDate = parseFormat.parse((String) userInfos.get("birthdate"));
            creationDate = parseFormat.parse((String) userInfos.get("creationTime"));
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }

        User user = new User((String)userInfos.get("email"), (String) userInfos.get("username"), (String) userInfos.get("country"), (String) userInfos.get("gender"), birthDate, creationDate) ;
    return user;
    }
}
