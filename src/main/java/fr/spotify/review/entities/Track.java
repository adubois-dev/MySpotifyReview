package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tracks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.AlbumResponseView.class)
    @Column(name="id")
    private Long id;

    @Column(name="name",length = 100)//, unique=true)
    @JsonView(Views.AlbumResponseView.class)
    private String name;

    @ManyToOne(targetEntity = Album.class)
    @JsonView(Views.AlbumResponseView.class)
    private Album album;

    @Column(name="uri", unique=true)
    private String trackURI;

    private Boolean localtrack;
    private Boolean episode;

    public Track(String name, String trackURI, Album album) {
        this.name = name;
        this.album=album;
        this.trackURI=trackURI;
    }

    public Track(String name) {
        this.name = name;
    }

}
