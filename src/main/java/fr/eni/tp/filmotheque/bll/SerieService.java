package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Serie;
import fr.eni.tp.filmotheque.dal.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
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
    public Serie saveSerie(Serie serie){
        return serieRepository.save(serie);
    }

    /**
     * Supprimer une série par son ID.
     */
    public void deleteSerie(Long id){
        serieRepository.deleteById(id);
    }
}
