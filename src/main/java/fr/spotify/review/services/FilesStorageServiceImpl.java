package fr.spotify.review.services;

import fr.spotify.review.entities.FileInfo;
import fr.spotify.review.entities.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private  Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      if(!Files.exists(root))Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public FileInfo save(MultipartFile file, User user, FileInfo fileInfo) {
    try {
      Path path=Paths.get(root.toString(), user.getUuid().toString());
      if(!Files.exists(path)) Files.createDirectory(path);
      fileInfo.setPath(path.resolve(file.getOriginalFilename()).toString());
      Files.copy(file.getInputStream(),path.resolve(file.getOriginalFilename()));
      return fileInfo;
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filePath) {
    try {

      Path file = Paths.get(filePath);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll(User owner) {
    try {

      Path pathroot=Paths.get(root.toString(), owner.getUuid().toString());
      if(!Files.exists(pathroot)) Files.createDirectory(pathroot);
      System.out.println(Files.list(pathroot).toList().get(0).toString());
      return Files.walk(pathroot, 1);//.filter(path -> !path.equals(pathroot)).map(pathroot::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

}
