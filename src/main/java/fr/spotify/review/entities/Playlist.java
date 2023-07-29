package fr.spotify.review.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="playlists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String name;
    @Column(name="last_modified_at")
    public Date lastModifiedDate;
    public String description;
    public Long numberOfFollowers;
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    public User user;
    public Long numberOfTracks;



    public Playlist(String name, User user, Date lastModifiedDate, String description, Long numberOfFollowers, Long numberOfTracks) {
        this.name = name;
        this.user= user;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.numberOfFollowers = numberOfFollowers;
    }

 }
