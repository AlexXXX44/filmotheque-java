package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.SerieService;
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
        return "series/view-serie-detail";
    }
}
