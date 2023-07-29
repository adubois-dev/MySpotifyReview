package fr.spotify.review.services;

import fr.spotify.review.entities.User;
import fr.spotify.review.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }


    public User findById(UUID id) {return this.userRepository.findByUuid(id).orElseThrow();}

    public List<User> findAll() { return this.userRepository.findAll();}

    public User findByUsername(String username) { return this.userRepository.findByUsername(username).orElseThrow();}
    public Optional<User> findByEmail(String email) { return this.userRepository.findByEmail(email);}


}
