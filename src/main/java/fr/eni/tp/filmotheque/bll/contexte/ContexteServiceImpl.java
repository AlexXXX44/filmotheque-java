package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.stereotype.Service;

@Service("contexteService") // ou @Service si tu n’utilises pas @Qualifier
public class ContexteServiceImpl implements ContexteService {

    @Override
    public Membre charger(String email) {
        // logique à définir, ici un mock ou une future recherche en base
        return null;
    }
}
