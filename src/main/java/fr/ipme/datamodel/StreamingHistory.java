package fr.ipme.datamodel;

public class StreamingHistory {
    public Artist artist;
    public SongTitle songTitle;

    public TimePlayed msplayed;
    public ListeningDate listeningDate;
    public StreamingHistory(Artist artist, SongTitle songTitle, TimePlayed msplayed, ListeningDate listeningDate) {
        this.artist=artist;
        this.songTitle=songTitle;
        this.msplayed=msplayed;
        this.listeningDate=listeningDate;
    }
}
