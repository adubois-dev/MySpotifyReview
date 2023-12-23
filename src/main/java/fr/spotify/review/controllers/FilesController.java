package fr.spotify.review.controllers;

import fr.spotify.review.entities.FileInfo;
import fr.spotify.review.entities.User;
import fr.spotify.review.exception.StatifyResourceNotFoundException;
import fr.spotify.review.exception.ResponseMessage;
import fr.spotify.review.jsonparsers.ParseHistorics;
import fr.spotify.review.jsonparsers.ParseLibrary;
import fr.spotify.review.jsonparsers.ParsePlaylists;
import fr.spotify.review.jsonparsers.ParseSpotifyUserInfos;
import fr.spotify.review.services.FileInfoService;
import fr.spotify.review.services.FilesStorageService;
import fr.spotify.review.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@CrossOrigin(origins={"http://localhost:4200","http://statifyfront:8081","http://localhost:8081","http://10.5.0.5:8081"}, allowCredentials = "true")
@RequestMapping("/api/upload")
@AllArgsConstructor
public class FilesController {

  private final FilesStorageService storageService;
  private final UserService userService;
  private final ParseSpotifyUserInfos spuParser;
  private final ParsePlaylists playlistParser;
  private final ParseLibrary libraryParser;
  private final ParseHistorics historicsParser;
  private final FileInfoService fileInfoService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("email") String email) {
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();
      User user = userService.findByEmail(email)
              .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned  empty result. No user found for email : " + email));
      System.out.println(email + "for user " + user.getUsername());
      Arrays.asList(files).stream().forEach(file -> {
        if(!fileInfoService.existsByFilenameAndUserUuid(file.getOriginalFilename(),user.getUuid())) {
          FileInfo fileInfo = new FileInfo(file.getOriginalFilename(), null, user);
          checkIfParseable(file, user);
          fileInfo = storageService.save(file, user, fileInfo);
          fileInfoService.save(fileInfo);
          fileNames.add(file.getOriginalFilename());
        }

      });

      message = "Uploaded the files successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  public void checkIfParseable(MultipartFile file, User user) {
    if(file.isEmpty()) return;
    Pattern playlist_pattern = Pattern.compile("Playlist\\d+.json");
    Pattern historic_pattern = Pattern.compile("StreamingHistory\\d+.json");
    Pattern full_historic_pattern = Pattern.compile("endsong_\\d+.json");
    Matcher m = playlist_pattern.matcher(file.getOriginalFilename());
    Matcher m2 = historic_pattern.matcher(file.getOriginalFilename());
    Matcher m3 = full_historic_pattern.matcher(file.getOriginalFilename());
    if (file.getOriginalFilename().equals("Userdata.json")) {
      System.out.println("User data uploaded, parsing...");
      spuParser.parseSpotifyUserInfos(user, file);
    }
    else if (m.matches()) {
      System.out.println("Playlist uploaded, parsing...");
      playlistParser.parsePlaylists(user, file);
    }
    else if (file.getOriginalFilename().equals("YourLibrary.json")) {
      System.out.println("Library uploaded, parsing...");
      libraryParser.parseLibrary(file);
    }
    else if (m2.matches()) {
      System.out.println("Historic uploaded, parsing...");
      historicsParser.parseHistoricVersion(user, file);
    }
    else if (m3.matches()) {
      System.out.println("Full Version Historic uploaded, parsing...");
      historicsParser.parseHistoricsFull(user, file);
    }

  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles(@RequestParam("email") String email) {
    User owner = userService.findByEmail(email)
            .orElseThrow(() -> new StatifyResourceNotFoundException("Query returned empty result. No User found for email : " + email));
    List<FileInfo> fileInfos = fileInfoService.findByUserUUID(owner.getUuid());
    List<FileInfo> fileInfosResult=new ArrayList<>();
    System.out.println(fileInfos.size());
    for(FileInfo file:fileInfos){
      String url = MvcUriComponentsBuilder
              .fromMethodName(FilesController.class, "getFile", file.getPath().toString()).build().toString();
      file.setUrl(url);
      fileInfoService.save(file);
      fileInfosResult.add(file);
    }
    return ResponseEntity.status(HttpStatus.OK).body(fileInfosResult);
  }

  @RequestMapping("/download")
  public ResponseEntity<Resource> getFile(String path) {
    Resource file = storageService.load(path);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
