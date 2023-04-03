package fr.spotify.review.domain;

//import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.spotify.review.Main.CONNECTION;
import static fr.spotify.review.Main.LOGGER;

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
    public User user;
    public Long numberOfTracks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Playlist(String name, User user, Date lastModifiedDate, String description, Long numberOfFollowers, Long numberOfTracks) {
        this.name = name;
        this.user=user;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.numberOfFollowers = numberOfFollowers;
    }

    public Playlist(Integer id, String name, Date lastModifiedDate, String description, Long numberOfFollowers, User user) {
        this.id = id;
        this.name = name;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.numberOfFollowers = numberOfFollowers;
        this.user = user;
    }

    public void insertAsNewPlaylist() throws SQLException {

        String changedPlaylistName = this.getName().replace("'"," ");
        Statement statement = CONNECTION.createStatement();
        ResultSet r = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM playlists WHERE playlists.name='" + changedPlaylistName + "';");
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        LOGGER.debug("NbLignes == " + count);
        if (count == 0) {
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
            LOGGER.debug(sm.format(this.getLastModifiedDate()));
            statement = CONNECTION.createStatement();
            statement.executeUpdate("INSERT INTO playlists(name, description, number_of_followers, last_modified_at, user_id) VALUES ('" + changedPlaylistName + "', '" + this.getDescription() +"', " + this.getNumberOfFollowers()+ ", '" + sm.format(this.getLastModifiedDate()) + "', " + this.getUser().getId() + ");");
            LOGGER.debug("Playlist insérée avec succès");
        } else LOGGER.debug("Déjà présente dans la base ! On annule ! :)");
    }
    public static Playlist getPlaylistByName(String playListName) throws SQLException {
        Statement statement = CONNECTION.createStatement();
        String changedPlaylistName = playListName.replace("'"," ");
        ResultSet rs = statement.executeQuery("SELECT * FROM playlists WHERE playlists.name='" + changedPlaylistName + "';");
        Playlist maPL = null;
        if (rs.next()) {
            maPL = new Playlist((Integer) rs.getInt("id"), rs.getString("name"), rs.getDate("last_modified_at"),  rs.getString("description"),  rs.getLong("number_of_followers"), User.getUserById(rs.getInt("user_id")));
            LOGGER.debug("found playlist : Playlist Name : " + rs.getString("name"));
        }
        return maPL;
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