package fr.ipme;

import fr.ipme.datamodel.Artist;
import fr.ipme.datamodel.StreamingHistory;
import fr.ipme.datamodel.ListeningDate;
import fr.ipme.datamodel.SongTitle;
import fr.ipme.datamodel.TimePlayed;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {

DatabaseConnect.databaseConnection();
ParseStreamingHistory.parseStreamingHistory();

    }
}