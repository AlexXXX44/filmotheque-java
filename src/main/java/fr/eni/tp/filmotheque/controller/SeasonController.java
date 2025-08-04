package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.SeasonService;
import fr.eni.tp.filmotheque.bll.SerieService;
import fr.eni.tp.filmotheque.bo.Season;
import fr.eni.tp.filmotheque.bo.Serie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonService seasonService;
    private final SerieService serieService;

    public SeasonController(SeasonService seasonService, SerieService serieService){
        this.seasonService = seasonService;
        this.serieService = serieService;
    }

    @GetMapping("/new/{serieId}")
    public String afficherNouvelleSeason(@PathVariable long serieId, Model model){
        Serie serie = serieService.findById(serieId).orElse(null);
        Season season = new Season();
        season.setSerie(serie);
        model.addAttribute("season", season);
        return "view-season-form";
    }

/*    public String afficherNouvelleSeason(@PathVariable long serieId, Model model){
        Serie serie = serieService.findById(serieId).orElse(null);
        if(serie != null){
            model.addAttribute("serie", serie);
            model.addAttribute("season", new fr.eni.tp.filmotheque.bo.Season());
            return "view-season-form";
        }
        return "redirect:/films";
    }

    public String creerSeason(@ModelAttribute("season") Season season, @PathVariable long serieId){}*/

    @PostMapping("/save")
    public String saveSeason(@ModelAttribute("season") Season season){
        seasonService.saveSeason(season);
//        return "redirect:/Series/detail/" + season.getSerie().getId() + "/seasons";
        return "redirect:/series/" + season.getSerie().getId();
    }
}
