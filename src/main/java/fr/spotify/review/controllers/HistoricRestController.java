package fr.spotify.review.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.entities.Historic;
import fr.spotify.review.entities.OutputAssessment;
import fr.spotify.review.entities.OutputMostPlayed;
import fr.spotify.review.entities.User;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.services.HistoricService;
import fr.spotify.review.services.UserService;
import fr.spotify.review.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins={"http://localhost:4200","http://statifyfront:8081","http://localhost:8081","http://10.5.0.5:8081"}, allowCredentials = "true")
@RequestMapping("api/historic")
public class HistoricRestController {

    private final HistoricService historicService;
    private final UserService userService;

    @Autowired
    public HistoricRestController(HistoricService historicService, UserService userService) {
        this.userService = userService;
        this.historicService = historicService;
    }

    @GetMapping
    @JsonView(Views.HistoricsView.class)
    public ResponseEntity<List<Historic>> getHistoricList() {
        return ResponseEntity.ok(this.historicService.getAll());
    }

    @GetMapping(path = "{id}")
    @JsonView(Views.HistoricsView.class)
    public ResponseEntity<Optional<Historic>> getHistoricById(@PathVariable Long id) {
        return ResponseEntity.ok(historicService.getHistoricById(id));
    }

    @GetMapping("/user/{id}")
    @JsonView(Views.HistoricsView.class)
    public ResponseEntity<List<Historic>> getHistoricListByUser(@PathVariable ("email") String email, @PathVariable("page") int page) {
        User user=userService.findByEmail(email)
                .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No User for email : " + email));

        Pageable firstPageWithFiftyElements = PageRequest.of(page, 50, Sort.by("datePlayed").descending());
        return ResponseEntity.ok(this.historicService.getHistoricByUserId(user.getUuid(), firstPageWithFiftyElements));
    }
    @GetMapping("/user/mostplayed/{uuid}")
    @JsonView(Views.HistoricsView.class)
    public ResponseEntity<List<OutputMostPlayed>> getUserMostPlayed(@PathVariable UUID uuid) {
        Pageable firstPage = PageRequest.of(0, 50);
        return ResponseEntity.ok(this.historicService.getUsersMostPlayed(uuid, firstPage));
    }

    @GetMapping("/user/assessment/{uuid}")
    public ResponseEntity<OutputAssessment> getUserAssessment(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.historicService.getUserAssessment(uuid));
    }
}
