package fr.spotify.review.database;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static fr.spotify.review.Main.log;


public class DatabaseConnect {

    public static Connection databaseConnection()

    {
        Connection connection=null;
        log.debug("CONNECT TO MariaDB. IT WORKS !!!");
        try {
            DataSourceInit.getDataSource();
            connection = DriverManager.getConnection("jdbc:mariadb://anonomous.fr:3307/myspotifydata", "development", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        log.debug("Connected to the  database");
    return connection;
    }


}
