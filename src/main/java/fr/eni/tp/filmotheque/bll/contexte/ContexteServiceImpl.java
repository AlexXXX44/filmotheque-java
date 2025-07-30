package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("contexteService")
@Profile("prod")
public class ContexteServiceImpl implements ContexteService {
    @Override
    public Membre charger(String email) {
        return null;
    }
    // implémentation des méthodes
}
