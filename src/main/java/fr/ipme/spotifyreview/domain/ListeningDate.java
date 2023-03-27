package fr.ipme.spotifyreview.domain;

//import javax.persistence.*;
import java.util.Date;
//@Entity
//@Table(name = "times_listened")
public class ListeningDate {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public Date listeningDate;
    public ListeningDate(Date listeningDate) {
        this.listeningDate=listeningDate;
    }

    @Override
    public String toString() {
        return "ListeningDate{" +
                "listeningDate=" + listeningDate.toString() +
                '}';
    }
}
