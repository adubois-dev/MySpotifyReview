package fr.spotify.review.services;

import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.FileInfo;
import fr.spotify.review.repositories.FileInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FileInfoService {

    @Autowired
    private final FileInfoRepository fileInfoRepository;
    public FileInfo save(FileInfo fileInfo) { return this.fileInfoRepository.save(fileInfo); }

    public Boolean existsByFilenameAndUserUuid(String filename, UUID uuid){return this.fileInfoRepository.existsByFilenameAndUserUuid(filename, uuid);}
    public Optional<FileInfo> findByFilenameAndUserUuid(String filename, UUID uuid){return this.fileInfoRepository.findByFilenameAndUserUuid(filename, uuid);}
    public List<FileInfo> findByFilename(String name) {
        return this.fileInfoRepository.findByFilename(name);
    }
    public List<FileInfo> findByUserUUID(UUID uuid) {
        return this.fileInfoRepository.findByUserUuid(uuid);
    }

}
