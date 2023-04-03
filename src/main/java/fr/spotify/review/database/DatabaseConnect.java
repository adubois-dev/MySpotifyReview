package fr.spotify.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static fr.spotify.review.Main.LOGGER;


public class DatabaseConnect {

    public static Connection databaseConnection()

    {
        Connection connection=null;
        LOGGER.debug("CONNECT TO MariaDB. IT WORKS !!!");
        try {
            DataSourceInit.getDataSource();
            connection = DriverManager.getConnection("jdbc:mariadb://statifydb/myspotifydata", "development", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        LOGGER.debug("Connected to the  database");
    return connection;
    }


}
