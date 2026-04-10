package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.SerieService;
import fr.eni.tp.filmotheque.bo.Serie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    @GetMapping
    public String afficherSeries(Model model){
        model.addAttribute("series", serieService.findAll());
        return "series/view-series";
    }

    @GetMapping("/{id}")
    public String afficherSerie(@PathVariable long id, Model model){
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

        return "series/view-series";
    }
}
