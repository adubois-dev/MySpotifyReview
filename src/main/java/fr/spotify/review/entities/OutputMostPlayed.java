package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import lombok.*;

//import static fr.spotify.review.Main.CONNECTION;
@NoArgsConstructor
@Getter
@Setter
@Data
public class OutputMostPlayed {

    @JsonView(Views.HistoricsView.class)
    private Long nbTimesPlayed;
    @JsonView(Views.HistoricsView.class)
    private Double totalTimePlayed;
    @JsonView(Views.HistoricsView.class)
    private Artist artist;
    @JsonView(Views.HistoricsView.class)
    private Track track;

    public OutputMostPlayed(Long nbTimesPlayed, Double totalTimePlayed, Artist artist, Track track) {
        this.nbTimesPlayed = nbTimesPlayed;
        this.totalTimePlayed = totalTimePlayed;
        this.artist = artist;
        this.track = track;
    }
}
