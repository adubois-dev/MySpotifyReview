package fr.spotify.review.controllers;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import fr.spotify.review.entities.SpotifyUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfosController {

    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins="http://localhost:8080")
    @GetMapping("/spUserinfos")
    public UserInfos spUserInfos(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email){ /*{
        String spUserInfo="";
        try {
            SpotifyUser spUser=SpotifyUser.getSpotifyUserByEmail(email);
                   if(spUser==null) spUserInfo="Something went wrong, the information you we're looking for apparently does not exist";
                   else spUserInfo=spUser.toString();
        } catch (SQLException e) {
            spUserInfo="Something went wrong, the information you we're looking for apparently does not exist";//throw new RuntimeException(e);
        }
*/
        return new UserInfos(counter.incrementAndGet(),"toto");
    }
}