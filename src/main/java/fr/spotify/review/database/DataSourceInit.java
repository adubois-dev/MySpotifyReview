package fr.spotify.review.database;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import static fr.spotify.review.Main.LOGGER;

public class DataSourceInit {

        public static DataSource getDataSource(){
            LOGGER.debug("connexion à la base de données");
            DataSource dataSource = createDataSource();
            DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
            return dataSource;
        }

        private static DatabasePopulator createDatabasePopulator() {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.setContinueOnError(true);
            databasePopulator.addScript(new ClassPathResource("myspotifydata.sql"));
            return databasePopulator;
        }

        private static DataSource createDataSource() {
            LOGGER.debug("renseignement des infos de la base de données");
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver");
            dataSourceBuilder.url("jdbc:mariadb://172.21.0.2");
            dataSourceBuilder.username("root");
            dataSourceBuilder.password("qwerty");
            return dataSourceBuilder.build();
        }
    }
