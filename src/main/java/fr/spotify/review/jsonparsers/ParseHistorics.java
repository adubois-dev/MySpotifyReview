package fr.spotify.review.jsonparsers;

import fr.spotify.review.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static fr.spotify.review.Main.conn;
import static fr.spotify.review.Main.log;

public class ParseHistorics {

    public static ArrayList<Historics> parseHistorics() throws SQLException {

        System.out.println("Delete all Historics;");
        Historics.DeleteAllHistos();

        System.out.println("PARSE Historics;");
        JSONParser jsonP = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        JSONArray jsonArray = null;
        ArrayList<Historics> historyList = new ArrayList<Historics>();
        //Open the Files
        for(int i=0;i<4;i++) {
            try {
                jsonArray = (JSONArray) jsonP.parse(new FileReader("RessourcesExterieures/MyData/StreamingHistory" + i + ".json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            //Parse it
            int len = jsonArray.size();
            for (int j = 0; j < len; j++) {
                JSONObject jsonO = (JSONObject) jsonArray.get(j);

                Artist artist = new Artist((String) jsonO.get("artistName"));
                Track title = new Track((String) jsonO.get("trackName"));
                System.out.println(title.getTrackName());
                Long msplayed = (Long) jsonO.get("msPlayed");
                DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = null;
                try {
                    date = parseFormat.parse((String) jsonO.get("endTime"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }
                Date listeningDate = date;
                Historics histo = new Historics(artist, title, User.getUserByEmail("adubois.personnel@gmail.com"), msplayed, listeningDate);
//            historyList.add(histo);
                System.out.println(histo.getTrack().getTrackName());
                //System.out.println(histo.toString());
                histo.insertAsNewHisto();
            }
        }
        return historyList;
    }
}