package fr.spotify.review.repositories;

import fr.spotify.review.entities.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

}
