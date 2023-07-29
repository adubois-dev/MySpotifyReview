package fr.spotify.review.services;

import fr.spotify.review.entities.*;
import fr.spotify.review.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoricService {

    private final HistoricRepository historicRepository;

    @Autowired
    public HistoricService(HistoricRepository historicRepository) {
        this.historicRepository = historicRepository;
    }

    public List<Historic> getAll() {
        return this.historicRepository.findAll();
    }

    public Optional<Historic> getHistoricById(Long id) {
        return this.historicRepository.findById(id);
    }

    public List<Historic> saveAll(ArrayList<Historic> historyList) { return this.historicRepository.saveAll(historyList); }
    public boolean findIfHistoricexists(LocalDateTime datePlayed, User user, Artist artist, Track track) { return this.historicRepository.existsByDatePlayedAndUserAndArtistAndTrack(datePlayed, user, artist, track);}

    public Historic save(Historic histo) {return this.historicRepository.save(histo);
    }

    public List<Historic> getHistoricByUserId(UUID uuid, Pageable pageable) {
        return this.historicRepository.findAllByUserUuid(uuid, pageable);
    }
    public List<OutputMostPlayed> getUsersMostPlayed(UUID id, Pageable pageable){
        return this.historicRepository.findUserMostPlayed(id, pageable);
    }

    public OutputAssessment getUserAssessment(UUID uuid) {
        return this.historicRepository.getUserAssessment(uuid);
    }
}
