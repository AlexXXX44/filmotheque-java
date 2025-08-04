package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Season findBySerieId(Long serieId);
}
