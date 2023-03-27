package fr.ipme.spotifyreview.domain;

//import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "playlist")
public class Playlist {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public String name;
    public Date lastModifiedDate;
    public String description;
    public Long numberOfFollowers;
    public Long numberOfTracks;

    public Playlist(String name, Date lastModifiedDate, String description, Long numberOfFollowers, Long numberOfTracks) {
        this.name = name;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfTracks = numberOfTracks;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='            " + name + '\'' +
                ", lastModifiedDate=            " + lastModifiedDate.toString() +
                ", description='      " + description + '\'' +
                ", numberOfFollowers=         " + numberOfFollowers.toString() +
                ", numberOfTracks=      " + numberOfTracks.toString() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(Long numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public Long getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(Long numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

}