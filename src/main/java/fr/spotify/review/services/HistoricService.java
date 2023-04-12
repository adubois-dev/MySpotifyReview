package fr.spotify.review.services;

import fr.spotify.review.entities.Historic;
import fr.spotify.review.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Historic save(Historic histo) {return this.historicRepository.save(histo);
    }
}
