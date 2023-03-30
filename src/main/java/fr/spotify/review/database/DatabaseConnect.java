package fr.spotify.review.database;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
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
            connection = DriverManager.getConnection("jdbc:mariadb://anonomous.fr:3307/myspotifydata", "development", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        log.debug("Connected to the  database");
    return connection;
    }
public static void instantiatePMF() {
    Properties properties = new Properties();
    properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "{my_implementation_pmf_class}");
    properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:mariadb://anonomous.fr:3307/myspotifydata");
    properties.setProperty("javax.jdo.option.ConnectionUserName", "development");
    properties.setProperty("javax.jdo.option.ConnectionPassword", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
    PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties);
}

}
