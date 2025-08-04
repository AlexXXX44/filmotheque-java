package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
