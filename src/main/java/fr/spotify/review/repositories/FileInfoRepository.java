package fr.spotify.review.repositories;

import fr.spotify.review.entities.FileInfo;
import fr.spotify.review.entities.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    List<FileInfo> findByFilename(String name);
    Optional<FileInfo> findByFilenameAndUserUuid(String filename, UUID uuid);
    Boolean existsByFilenameAndUserUuid(String filename, UUID uuid);
    List<FileInfo> findByUserUuid(UUID uuid);
}
