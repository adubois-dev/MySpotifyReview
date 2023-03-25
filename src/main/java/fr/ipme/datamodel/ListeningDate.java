package fr.ipme.datamodel;

import java.util.Date;

public class ListeningDate {
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
