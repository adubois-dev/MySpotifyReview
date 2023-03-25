package fr.ipme;

import 0fr.ipme.datamodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParsePlaylists {

    public static ArrayList<StreamingHistory> parseStreamingHistory(){
        //PARSE StreamingHistory;
        JSONParser jsonP = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        JSONArray jsonArray = null;
        //Open the File
        try {
            jsonArray = (JSONArray) jsonP.parse(new FileReader("RessourcesExterieures/MyData/StreamingHistory0.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<StreamingHistory> historyList= new ArrayList<StreamingHistory>();
        //Parse it
        int len = jsonArray.size();
        for (int i = 0; i < len; i++) {
            JSONObject jsonO = (JSONObject) jsonArray.get(i);

            Artist artist =  new Artist((String) jsonO.get("artistName"));
            SongTitle title = new SongTitle((String) jsonO.get("trackName"));
            TimePlayed msplayed = new TimePlayed((Long)jsonO.get("msPlayed"));
            DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = null;
            try {
                date = parseFormat.parse((String) jsonO.get("endTime"));
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
            ListeningDate listeningDate = new ListeningDate(date);
            historyList.add(new StreamingHistory(artist, title, msplayed, listeningDate));

        }
        for(int i=0;i< historyList.size();i++){
            System.out.println(historyList.get(i).artist.toString());
        }
        for(int i=0;i< historyList.size();i++){
            System.out.println(historyList.get(i).listeningDate.toString());
        }
    return historyList;
    }
}
