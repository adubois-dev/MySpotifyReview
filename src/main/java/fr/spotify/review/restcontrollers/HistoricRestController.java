package fr.spotify.review.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Historic;
import fr.spotify.review.services.HistoricService;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/historic")
public class HistoricRestController {

    private final HistoricService historicService;

    @Autowired
    public HistoricRestController(HistoricService historicService) {
        this.historicService = historicService;
    }

    @GetMapping
    @JsonView(Views.AlbumResponseView.class)
    public List<Historic> getHistoricList() {
        return this.historicService.getAll();
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.AlbumResponseView.class)
    public Optional<Historic> getHistoricById(@PathVariable Long id) {
        return historicService.getHistoricById(id);
    }
}
