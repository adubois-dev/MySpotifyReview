package fr.spotify.review.entities.searcharray;

import fr.spotify.review.entities.FileInfo;

import java.util.Collection;

public final class FileInfoUtils {
    public static FileInfo findByFilename(Collection<FileInfo> listFiles, String filename) {
        return FindUtils.findByProperty(listFiles, file -> filename.equals(file.getFilename()));
    }


}
