package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="historics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.AlbumResponseView.class)
    private Long id;

    private Double msplayed;

    @ManyToOne(targetEntity = Artist.class)
    @JoinColumn(name="artist_id")
    @JsonView(Views.HistoricsView.class)
    private Artist artist;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_uuid")
    @JsonView(Views.HistoricsView.class)
    private User user;

    @ManyToOne(targetEntity = Track.class)
    @JoinColumn(name="track_id")
    @JsonView(Views.HistoricsView.class)
    private Track track;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name="album_id")
    @JsonView(Views.HistoricsView.class)
    private Album album;

    @JsonView(Views.HistoricsView.class)
    @Column(name="end_time")
    private LocalDateTime datePlayed;

    public Historic(Album album, Artist artist, Track track, User user, Double msplayed, LocalDateTime datePlayed) {
        this.msplayed = msplayed;
        this.artist = artist;
        this.user = user;
        this.track = track;
        this.datePlayed = datePlayed;
        this.album = album;
    }

}
