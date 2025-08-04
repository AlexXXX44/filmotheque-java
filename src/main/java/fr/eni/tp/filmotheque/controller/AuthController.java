package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"membreEnSession"})
public class AuthController {
    
    @Autowired
    private MembreRepository membreRepository;
    
    @GetMapping("/login")
    public String afficherLogin() {
        return "view-login_form";
    }
    
    @PostMapping("/login")
    public String connexion(@RequestParam String email, 
                          @RequestParam String motDePasse,
                          Model model) {
        Membre membre = membreRepository.findByEmail(email);
        if (membre != null && membre.getMotDePasse().equals(motDePasse)) {
            model.addAttribute("membreEnSession", membre);
            return "redirect:/films";
        }
        return "redirect:/login?error=true";
    }
}
