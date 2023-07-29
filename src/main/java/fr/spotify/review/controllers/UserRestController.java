package fr.spotify.review.controllers;

import fr.spotify.review.entities.User;
import fr.spotify.review.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/User")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User User = this.userService.findByUsername(username);

        return ResponseEntity.ok(User);
    }

}
