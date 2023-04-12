package fr.spotify.review.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="spotify_user")
public class SpotifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    @Column(name="spotify_username")
    private String spotifyUsername;
    private String country;
    private String gender;
    private Date birthdate;
    private Date creationTime;

    @OneToMany(targetEntity = Historic.class)
    @JoinColumn(name="id")
    private List<Historic> histos = new ArrayList<>();

    public SpotifyUser(String email, String spotify_username, String country, String gender, Date birthdate, Date creationTime) {
        this.email = email;
        this.spotifyUsername = spotify_username;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.creationTime = creationTime;
    }
}
