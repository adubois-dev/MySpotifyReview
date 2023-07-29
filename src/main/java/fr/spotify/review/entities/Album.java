package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="albums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonView({Views.AlbumResponseView.class, Views.HistoricsView.class})
    private Long id;

    @JsonView({Views.AlbumResponseView.class, Views.HistoricsView.class})
    @Column(unique=true)
    private String name;

    @ManyToOne(targetEntity = Artist.class)
 //   @JsonView(Views.AlbumResponseView.class)
    private Artist artist;

    @OneToMany(targetEntity = Track.class, mappedBy = "album")
    @JsonView(Views.AlbumResponseView.class)
    private List<Track> tracks = new ArrayList<>();

    public Album(String name, Artist artist) {
        this.name = name;
        this.artist=artist;
    }

    @Override
    public String toString() {
        String str= getClass().getSimpleName() + "(" +"id = " + id + ", " +"name = " + name + ", " +"artist = " + artist.getName() + ")"+ "Tracklist :";
        return str;
    }
    public boolean equals(Album album){
        if (this.getName()==album.getName() && this.getId()==album.getId() && this.getArtist().equals(album.getArtist())) return true;
        else return false;
    }

}
