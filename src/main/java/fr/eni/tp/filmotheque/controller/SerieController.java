package fr.eni.tp.filmotheque.controller;

import org.springframework.data.domain.Page;

import fr.eni.tp.filmotheque.bll.SerieService;
import fr.eni.tp.filmotheque.bo.Serie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    @GetMapping("/create")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("serie", new Serie());
        // On récupère tous les genres pour les proposer dans le formulaire
        model.addAttribute("allGenres", serieService.findAllGenres()); 
        return "series/view-serie-create";
    }
    
    @GetMapping
    public String afficherSeries(
    @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
    @RequestParam(name = "size", defaultValue = "5") int size,
    @RequestParam(name = "keyword", defaultValue = "") String keyword,
    @RequestParam(name = "noGenre", required = false) Integer noGenre,
    Model model) {

        // On récupère une Page au lieu d'une List
        Page<Serie> pageSeries = serieService.findFiltered(keyword, noGenre, currentPage, size);

        model.addAttribute("series", pageSeries.getContent()); // Les données
        model.addAttribute("totalPages", pageSeries.getTotalPages()); // Pour ton affichage de pagination
        model.addAttribute("currentPage", currentPage);
        
            model.addAttribute("keyword", keyword); // Pour réafficher le mot-clé dans le champ de recherche
            model.addAttribute("noGenre", noGenre); // Pour réafficher le genre sélectionné dans le menu déroulant
            model.addAttribute("genres", serieService.findAllGenres()); // Pour remplir le menu dérou
        //model.addAttribute("series", serieService.findAll());
        return "series/view-series";
    }

    @GetMapping("/{id}")
    public String afficherSerie(@PathVariable("id") long id, Model model) {
        serieService.findById(id).ifPresent(s -> model.addAttribute("serie", s));
        // Récupère la série (404 si introuvable)
        Serie serie = serieService.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Serie not found"));

        // Ajoute l’attribut EXACTEMENT sous la clé "serie"
        model.addAttribute("serie", serie);

        // Optionnel: exposer le nombre de saisons côté modèle (si vous préférez l’utiliser dans le template)
//        int seasonCount = (serie.getSeasons() != null) ? serie.getSeasons().size() : 0;
//        model.addAttribute("seasonCount", seasonCount);

        return "series/view-serie-details";
    }
}
