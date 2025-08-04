package fr.eni.tp.filmotheque.controller;

import java.util.List;

import fr.eni.tp.filmotheque.bo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import fr.eni.tp.filmotheque.bll.FilmService;

@Controller
@RequestMapping("/films")
// Injection de la liste des attributs en session
@SessionAttributes({ "genresEnSession", "membreEnSession", "participantsEnSession" })
public class FilmController {

	private final FilmService filmService;

	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping
	public String afficherFilms(
//			@ModelAttribute("films")
			Model model) {
		System.out.println("Tous les films : ");
		List<Film> films = filmService.consulterFilms();
		// Ajout des films dans le modèle
		model.addAttribute("films", films);
		return "view-films";
	}

	@GetMapping("/detail")
	public String afficherUnFilm(@RequestParam(name = "id") long id, Model model) {
		if (id > 0) {// L'identifiant en base commencera en 1
			Film film = filmService.consulterFilmParId(id);
			if (film != null) {
				// Ajout de l'instance dans le modèle
				model.addAttribute("film", film);
				return "view-film-detail";
			} else
				System.out.println("Film inconnu!!");
		} else {
			System.out.println("Identifiant inconnu");
		}
		return "redirect:/films";
	}

	@ModelAttribute("genresEnSession")
	public List<Genre> chargerGenres() {
		System.out.println("Chargement en Session - GENRES");
		return filmService.consulterGenres();
	}

	@GetMapping("/creer")
	public String creerFilm(Model model, @ModelAttribute("membreEnSession") Membre membreEnSession) {
		if (membreEnSession != null && membreEnSession.getId() >= 1 && membreEnSession.isAdmin()) {
				// Ajout de l'instance dans le modèle
			model.addAttribute("film", new Film());
            return "view-film-form"; //return "film/creation";
		} else {
			// redirection vers la page des films
			return "redirect:/films";
		}
	}

//	ette méthode pour gérer le cas où membreEnSession n'existe pas
	@ExceptionHandler(HttpSessionRequiredException.class)
	public String handleSessionRequiredException() {
		return "redirect:/login";
	}

	// Création d'un nouveau film
	@PostMapping("/creer")
	public String creerFilm(@ModelAttribute("film") Film film,
						@ModelAttribute("membreEnSession") Membre membreEnSession) {
		if (membreEnSession != null && membreEnSession.getId() > 1 && membreEnSession.isAdmin()) {
			System.out.println(film);
			filmService.creerFilm(film);
			return "redirect:/films";
		} else {
			System.out.println("Aucun membre en session");
			return "redirect:/films";
		}
	}

	// Injection en session des listes représentant les participants
	@ModelAttribute("participantsEnSession")
	public List<Participant> chargerParticipants() {
		System.out.println("Chargement en Session - PARTICIPANTS");
		return filmService.consulterParticipants();
	}

	

}
