package fr.spotify.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static fr.spotify.review.Main.LOGGER;


public class DatabaseConnect {

    public static Connection databaseConnection()

    {
        Connection connection=null;
        try {
            DataSourceInit.getDataSource();
            connection = DriverManager.getConnection("jdbc:mariadb://172.21.0.2/myspotifydata", "development", "wUWDRcJTIHfCu4nj3PaWSVB46pvJw70hDItD78v8dJ");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
