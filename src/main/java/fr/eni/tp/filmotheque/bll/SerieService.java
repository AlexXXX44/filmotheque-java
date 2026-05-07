package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Serie;
import fr.eni.tp.filmotheque.dal.SerieRepository;
import fr.eni.tp.filmotheque.dal.GenreRepository; // Nécessaire pour récupérer les genres
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final GenreRepository genreRepository; // Nécessaire pour récupérer les genres

    public SerieService(SerieRepository serieRepository, GenreRepository genreRepository) {
        this.serieRepository = serieRepository;
        this.genreRepository = genreRepository;
    }

    public Page<Serie> findFiltered(String keyword, Integer genreId, int page, int size) {
        // Attention : en JPA les pages commencent à 0, d'où le "page - 1"
        Pageable pageable = PageRequest.of(page - 1, size);
        return serieRepository.findByFilters(keyword, genreId, pageable);
    }
    
    // N'oublie pas d'ajouter cette méthode si elle manque pour ton menu déroulant
    public List<Genre> findAllGenres() {
        return genreRepository.findAll(); // Nécessite l'injection de GenreRepository
    }
    /**
     * Récupérer toutes les séries.
     */
    public List<Serie> findAll() {
        return serieRepository.findAll();
    }

    /**
     * Rcupérer une série par son ID.
     */
    public Optional<Serie> findById(Long id) {
        return serieRepository.findById(id);
    }

    /**
     * Enregistrer ou mettre à jour une série.
     */
    public Serie saveSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    /**
     * Supprimer une série par son ID.
     */
    public void deleteSerie(Long id){
        serieRepository.deleteById(id);
    }
}
