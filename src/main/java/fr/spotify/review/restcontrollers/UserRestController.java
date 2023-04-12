package fr.spotify.review.restcontrollers;


import fr.spotify.review.entities.User;
import fr.spotify.review.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUserList() {
        return this.userService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        Optional<User> user = this.userService.getById(id);

        return user;
    }

}
