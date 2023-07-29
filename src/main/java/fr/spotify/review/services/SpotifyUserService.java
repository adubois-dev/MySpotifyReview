package fr.spotify.review.services;


import fr.spotify.review.entities.SpotifyUser;
import fr.spotify.review.repositories.SpotifyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpotifyUserService {

    private final SpotifyUserRepository spUserRepository;

    @Autowired
    public SpotifyUserService(SpotifyUserRepository spUserRepository) {
        this.spUserRepository = spUserRepository;
    }

    public List<SpotifyUser> getAll() {
        return this.spUserRepository.findAll();
    }

    public Optional<SpotifyUser> getById(Long id) {
        return this.spUserRepository.findById(id);
    }
    public SpotifyUser findSpotifyUserByEmail(String email){ return this.spUserRepository.findByEmail(email);}
    public boolean existsByEmail(String email) { return this.spUserRepository.existsByEmail(email);}
    public SpotifyUser save(SpotifyUser spUser) {return this.spUserRepository.save(spUser);}

    public SpotifyUser findByUserName(String username) { return this.spUserRepository.findBySpotifyUsername(username);}

    public SpotifyUser findByUserEmail(String email) { return this.spUserRepository.findByUserEmail(email);}

    public SpotifyUser findByUserUuid(UUID uuid) { return this.spUserRepository.findByUserUuid(uuid);}
}
