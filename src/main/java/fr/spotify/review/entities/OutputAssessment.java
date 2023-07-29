package fr.spotify.review.entities;

import lombok.*;

//import static fr.spotify.review.Main.CONNECTION;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class OutputAssessment {

    public Long nbScrobbles;
    public Double totalTimePlayed;
    public Long nbArtists;
    public Long nbTracks;

}
