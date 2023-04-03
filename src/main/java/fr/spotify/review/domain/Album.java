package fr.spotify.review.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static fr.spotify.review.Main.CONNECTION;
import static fr.spotify.review.Main.LOGGER;
public class Album {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String albumName;
    public Artist performer;
    ;

    public Album(int id, String albumName) {
        this.id = id;
        this.albumName = albumName;
    }

    public Album(String albumName, Artist performer) {
        this.performer = performer;
        this.albumName = albumName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        this.performer = performer;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumName='" + albumName + "' Performed by" + performer.toString();
    }

    public int insertAsNewAlbum() throws SQLException {

        // First, we check if the album is linked to an artist
        //if it is null we leave as no album won't be attached to no artist
        String changedAlbumName = this.getAlbumName().replace("'"," ");

        if (this.getPerformer() == null) {
            LOGGER.debug("Album sans artiste associé. Ne sera donc pas inséré");
        } else {
            Statement statement = CONNECTION.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM albums WHERE albums.name='" + changedAlbumName + "';");
            String oldPerformer="";

            while (rs.next()) { //If any of the already present in the databasealbums
                if (rs.getString("name") == this.getPerformer().getArtistName() && rs.getString("name")!="") {
                    LOGGER.debug("il s'agit du même artiste, l'album ne sera donc pas inséré");
                    return 0;
                } else {
                    oldPerformer += rs.getString("name") + "   |   ";
                }
            }
            statement = CONNECTION.createStatement();
            statement.executeUpdate("INSERT INTO albums(name, artist_id) " + "VALUES ('" + changedAlbumName + "', " + this.getPerformer().getId() + ");");
            LOGGER.debug("Album inséré avec succès. L'album est disponible avec 2 artistes différents ! Exclu Statify !!! Performed by " + this.getPerformer().getArtistName() + " & by " + oldPerformer);
            CONNECTION.commit();
            return 0;
        }
        return 0;
    }


public static Album getAlbumByName(String albumName) throws SQLException {

        String changedAlbumName = albumName.replace("'"," ");

        Statement statement = CONNECTION.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM albums WHERE albums.name='" + changedAlbumName + "';");
        Album album = null;
        if (rs.next()) {
        album = new Album((Integer) rs.getInt("id"), rs.getString("name"));
        LOGGER.debug("found album : Album Name : " + rs.getString("name"));
        }
        return album;
        }

public static Album getAlbumById(Integer id) throws SQLException {
        Statement statement = CONNECTION.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM albums WHERE id=" + id + ";");
        Album album = null;
        if (rs.next()) {
        album = new Album((Integer) rs.getInt("id"), rs.getString("name"));
        LOGGER.debug("found album : Album Name : " + rs.getString("name"));
        }
        return album;
        }
        }


