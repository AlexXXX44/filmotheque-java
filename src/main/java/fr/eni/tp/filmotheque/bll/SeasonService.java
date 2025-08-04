package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Season;
import fr.eni.tp.filmotheque.dal.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public Season getSeasonsBySerieId(Long serieId) {
        return seasonRepository.findBySerieId(serieId);
    }

    public Optional<Season> getSeasonById(int id) {
        return seasonRepository.findById(id);
    }

    public Iterable<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
    }

//    public Season deleteSeason(Long id) {
//        return seasonRepository.delete(id);
//    }
}
