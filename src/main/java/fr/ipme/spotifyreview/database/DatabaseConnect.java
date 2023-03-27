package fr.ipme.spotifyreview.database;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnect {

    public static void databaseConnection()

    {
        //CONNECT TO MariaDB. IT WORKS !!!
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SERVER_IP_ADDRESS/myspotifydata", "development", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }
public static void instantiatePMF() {
    Properties properties = new Properties();
    properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "{my_implementation_pmf_class}");
    properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:mariadb://SERVER_IP_ADDRESS/myspotifydata");
    properties.setProperty("javax.jdo.option.ConnectionUserName", "development");
    properties.setProperty("javax.jdo.option.ConnectionPassword", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
    PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties);
}

}
