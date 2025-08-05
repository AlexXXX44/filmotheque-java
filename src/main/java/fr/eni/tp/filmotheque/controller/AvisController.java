package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Membre;

@RequestMapping("/avis") // Injection de la liste des attributs en session
@SessionAttributes({ "membreEnSession" })
public class AvisController {

	private FilmService filmService;
	
	public AvisController(FilmService filmService) {
		this.filmService = filmService;
	}

	// Création d'un nouvel avis
	@GetMapping("/creer")
	public Object creerAvis(@RequestParam("idFilm") long idFilm, Model model,
	@ModelAttribute("membreEnSession") Membre membreEnSession) {
		if (membreEnSession != null && membreEnSession.getId() >= 1) {

		// Mise en session du titre du film sélectionné et de son id
			String titre = filmService.consulterTitreFilm(idFilm);
			if ( titre != null) {
				model.addAttribute("titreFilm", titre);
				model.addAttribute("idFilm", idFilm);
				Film membre = new Film();
				Membre membre1 = new Membre();
				Avis avis = new Avis(4, "On rit du début à la fin", membre, membre1);
				// Ajout de l'instance du formulaire dans le modèle
				model.addAttribute("avis", avis);
				return new ModelAndView("view-avis-form", "modelAvis", avis);
			}
		}
		return "redirect:/films";
	}

	// Récupération de l'objet opinion du formulaire
	@PostMapping("/creer")
	public String creerAvis(@ModelAttribute("avis") Avis avis,
		@RequestParam(name = "idFilm", required = true) long idFilm,
		@ModelAttribute("membreEnSession") Membre membreEnSession) {
		if (membreEnSession != null && membreEnSession.getId() >= 1) {
			avis.setMembre(membreEnSession);
			System.out.println(avis);
			// Sauvegarde de l’avis avec l’identifiant du film :
			filmService.publierAvis(avis, idFilm);
		}
		// Redirection vers la liste des films :
		return "redirect:/films";
	}
}
