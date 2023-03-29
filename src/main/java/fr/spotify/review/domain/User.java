package fr.spotify.review.domain;

import org.mariadb.jdbc.client.result.ResultSetMetaData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;


public class User {


    private Integer id;
    public String userName;
    public String password;
    public   String email;
    public String spotifyUserName;
    public String country;
    public String gender;
    public Date birthdate;
    public Date creationTime;

    public User(String userName, String email) {
        System.out.println("We will send him a temporary password by email at his first inscription");
        this.userName = userName;
        this.email = email;
    }

    public User(Integer id, String email) {
        System.out.println("We will send him a temporary password by email at his first inscription");
        this.id = id;
        this.email = email;
    }
    public User(String email) {
        System.out.println("We will send him a temporary password by email at his first inscription");
        this.email = email;
    }

    public User(Integer id, String userName, String password, String email, String spotifyUserName, String country, String gender, Date birthdate, Date creationTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.spotifyUserName = spotifyUserName;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.creationTime = creationTime;
    }

    public User(String email, String spotifyUserName, String country, String gender, Date birthdate, Date creationTime) {
        this.email = email;
        this.spotifyUserName = spotifyUserName;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.creationTime = creationTime;
    }


    public void insertAsNewUserFromJSON() throws SQLException {
        Statement statement = conn.createStatement();

        System.out.println("On trie sur le champ email puisqu'il s'agit du seul champ unique que l'on a à la fois lors d'une inscription et lors d'un import de données.");

        ResultSet r = statement.executeQuery("SELECT COUNT(*) AS recordCount FROM users WHERE users.email='" + this.getEmail() + "';");
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        System.out.println("NbLignes == " + count);
        if (count == 0) {
            statement = conn.createStatement();
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
            statement.executeUpdate("INSERT INTO users(email, spotify_username, country, gender, birthdate, creationTime) VALUES ('" + this.getEmail() + "', '" + this.getSpotifyUserName()  + "', '" + this.getCountry() + "', '" + this.getGender() + "', DATE '" + sm.format(this.birthdate)  + "', DATE '" + sm.format(this.getCreationTime()) + "') ;");
            System.out.println("Nouvel utilisateur inséré avec succès");
            conn.commit();
       } else System.out.println("Un utilisateur avec le même email est déjà présent dans la base");
    }


    public static User getUserByEmail(String userMail) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE users.email='" + userMail + "';");
        User user = null;
        if (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("passwd"),rs.getString("email"), rs.getString("spotify_username"), rs.getString("country"), rs.getString("gender"), rs.getDate("birthDate"), rs.getDate("creationTime"));
            }
        return user;
    }

    public static User getUserById(Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id=" + id + ";");
        User user = null;
        if (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("passwd"),rs.getString("email"), rs.getString("spotify_username"), rs.getString("country"), rs.getString("gender"), rs.getDate("birthDate"), rs.getDate("creationTime"));
        }
        return user;
    }


//Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpotifyUserName() {
        return spotifyUserName;
    }

    public void setSpotifyUserName(String spotifyUserName) {
        this.spotifyUserName = spotifyUserName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {

        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getCreationTime() {

        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
