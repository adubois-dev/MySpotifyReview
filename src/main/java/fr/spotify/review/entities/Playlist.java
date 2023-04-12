package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="playlists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public String name;
    @Column(name="last_modified_at")
    public Date lastModifiedDate;
    public String description;
    public Long numberOfFollowers;
    @ManyToOne
    @JoinColumn(name = "spotify_user_id")
    public SpotifyUser spUser;
    public Long numberOfTracks;



    public Playlist(String name, SpotifyUser spUser, Date lastModifiedDate, String description, Long numberOfFollowers, Long numberOfTracks) {
        this.name = name;
        this.spUser= spUser;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.numberOfFollowers = numberOfFollowers;
    }

 }
