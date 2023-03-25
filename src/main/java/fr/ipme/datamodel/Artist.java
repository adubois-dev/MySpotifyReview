package fr.ipme.datamodel;

public class Artist {
    public String artistName;
    public Artist(String artistName) {
        this.artistName=artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }
}
