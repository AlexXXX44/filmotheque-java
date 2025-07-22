package fr.eni.tp.filmotheque.bll;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

public interface FilmService {
	List<Film> consulterFilms();

	Film consulterFilmParId(long id);

	List<Genre> consulterGenres();

	List<Participant> consulterParticipants();

	Genre consulterGenreParId(long id);

	Participant consulterParticipantParId(long id);
	
	String consulterTitreFilm(long id);

	List<Avis> consulterAvis(long idFilm);

	void publierAvis(Avis avis, long idFilm);

	void creerFilm(Film film);
}
