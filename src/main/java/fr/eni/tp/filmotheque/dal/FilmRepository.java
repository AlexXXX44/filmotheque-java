package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
