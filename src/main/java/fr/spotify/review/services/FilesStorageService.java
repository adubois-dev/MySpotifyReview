package fr.spotify.review.services;

import fr.spotify.review.entities.FileInfo;
import fr.spotify.review.entities.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  public void init();

  public FileInfo save(MultipartFile file, User user, FileInfo fileInfo);

  public Resource load(String filePath);

  public void deleteAll();

  public Stream<Path> loadAll(User owner);
}
