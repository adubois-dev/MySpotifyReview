package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="spotify_users")
public class SpotifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonView(Views.HistoricsView.class)
    private String email;
    @Column(name="spotify_username")
    @JsonView(Views.HistoricsView.class)
    private String spotifyUsername;
    private String country;
    private String gender;
    private Date birthdate;
    private Date creationTime;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="user_uuid")
    private User user;


    public SpotifyUser(String email, String spotify_username, String country, String gender, Date birthdate, Date creationTime, User user) {
        this.email = email;
        this.spotifyUsername = spotify_username;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.creationTime = creationTime;
        this.user = user;
    }
}
