package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    
    // Une requête qui filtre par titre (contenant le mot-clé) 
    // ET par l'ID du genre (si présent dans la liste des genres)
    @Query("SELECT DISTINCT s FROM Serie s " +
           "LEFT JOIN s.genres g " +
           "WHERE (:keyword IS NULL OR s.name LIKE %:keyword%) " +
           "AND (:genreId IS NULL OR g.id = :genreId)")
    Page<Serie> findByFilters(@Param("keyword") String keyword, 
                              @Param("genreId") Integer genreId, 
                              Pageable pageable);
}
