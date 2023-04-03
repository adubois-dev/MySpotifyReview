package fr.spotify.review.jsonparsers;

import fr.spotify.review.domain.Artist;
import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.Track;
import fr.spotify.review.domain.User;
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

import static fr.spotify.review.Main.LOGGER;

public class ParseHistorics {

    public static ArrayList<Historics> parseHistorics() throws SQLException {

        LOGGER.debug("Delete all Historics;");
        User user = User.getUserByEmail("adubois.personnel@gmail.com");
        Historics.DeleteAllHistos(user);
        LOGGER.debug("PARSE Historics;");
        JSONParser jsonP = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        JSONArray jsonArray = null;
        ArrayList<Historics> historyList = new ArrayList<Historics>();
        //Open the Files
        for(int i=0;i<4;i++) {
            try {
                jsonArray = (JSONArray) jsonP.parse(new FileReader("/mnt/docker/MyData/StreamingHistory" + i + ".json"));
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
                LOGGER.debug(title.getTrackName());
                Long msplayed = (Long) jsonO.get("msPlayed");
                DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = null;
                try {
                    date = parseFormat.parse((String) jsonO.get("endTime"));
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }
                Date listeningDate = date;
                Historics histo = new Historics(artist, title, user, msplayed, listeningDate);
//            historyList.add(histo);
                LOGGER.debug(histo.getTrack().getTrackName());
                //LOGGER.debug(histo.toString());
                histo.insertAsNewHisto();
            }
        }
        return historyList;
    }
}