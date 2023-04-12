package fr.spotify.review.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name="playlistTracks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    public Playlist playlist;
    @ManyToOne
    @JoinColumn(name = "track_id")
    public Track track;
    public Date addedDate;

    public PlaylistTrack(Track track, Playlist maPL, Date addedDate) {
        this.track = track;
        this.playlist=maPL;
        this.addedDate = addedDate;
    }



}
