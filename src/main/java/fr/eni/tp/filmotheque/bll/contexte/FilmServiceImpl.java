package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private MembreRepository membreRepository;

    public FilmServiceImpl() {
    }

    @Override
    public List<Film> consulterFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film consulterFilmParId(long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> consulterGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Participant> consulterParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        return participantRepository.findById(id).orElse(null);
    }

    @Override
    public String consulterTitreFilm(long id) {
        Film film = consulterFilmParId(id);
        return film != null ? film.getTitre() : null;
    }

    @Override
    public List<Avis> consulterAvis(long idFilm) {
        return avisRepository.findAll().stream()
                .filter(a -> a.getFilm().getId() == idFilm)
                .toList();
    }

    @Override
    public void publierAvis(Avis avis, long idFilm) {
        Film film = consulterFilmParId(idFilm);
        avis.setFilm(film);
        avisRepository.save(avis);
    }

    @Override
    public void creerFilm(Film film) {

    }
}