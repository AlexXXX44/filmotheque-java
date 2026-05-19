package fr.eni.tp.filmotheque.controller;

import java.util.List;

import fr.eni.tp.filmotheque.bo.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
			model.addAttribute("membreEnSession", membreEnSession);
			model.addAttribute("genresEnSession", chargerGenres());
			model.addAttribute("participantsEnSession", chargerParticipants());
			model.addAttribute("allGenres", filmService.consulterGenres());	
			model.addAttribute("film", new Film());
            return "view-film-form"; //return "film/creation";
		} else {
			// redirection vers la page des films
			return "redirect:/films";
		}
	}
	
	@PostMapping("/enregistrer")
	public String enregistrerFilm(
			@Valid @ModelAttribute("film") Film film, 
			BindingResult result, 
			Model model,
			@ModelAttribute("membreEnSession") Membre membreEnSession) {
		
		// Sécurité également à la soumission
		if (membreEnSession == null || !membreEnSession.isAdmin()) {
			return "redirect:/films";
		}

		// 2. Si la validation échoue (ex: résumé vide ou durée manquante)
		if (result.hasErrors()) {
			// TRÈS IMPORTANT : Recharger la liste des genres pour ne pas qu'elle disparaisse à l'écran
			model.addAttribute("allGenres", filmService.consulterGenres());
			return "view-film-form";
		}

		// 3. Si tout est OK, sauvegarde et redirection
		filmService.save(film);
		return "redirect:/films";
	}

	// Injection en session des listes représentant les participants
	@ModelAttribute("participantsEnSession")
	public List<Participant> chargerParticipants() {
		System.out.println("Chargement en Session - PARTICIPANTS");
		return filmService.consulterParticipants();
	}
}
