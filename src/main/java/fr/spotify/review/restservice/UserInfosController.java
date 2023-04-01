package fr.spotify.review.restservice;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import fr.spotify.review.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfosController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/userinfos")
    public UserInfos userInfos(@RequestParam(value = "email", defaultValue = "adubois.personnel@gmail.com") String email) {
        String userInfo="";
        try {
            User user=User.getUserByEmail(email);
                   if(user==null) userInfo="Something went wrong, the information you we're looking for apparently does not exist";
                   else userInfo=user.toString();
        } catch (SQLException e) {
            userInfo="Something went wrong, the information you we're looking for apparently does not exist";//throw new RuntimeException(e);
        }

        return new UserInfos(counter.incrementAndGet(), userInfo );
    }
}