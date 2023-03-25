package fr.ipme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    public static void databaseConnection()

    {
        //CONNECT TO MariaDB. IT WORKS !!!
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://anonomous.fr:3307/myspotifydata", "development", "IgKe7xexdPtZp4ZbF0VbG2dwH8mbI24EZ1buStINgT");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
