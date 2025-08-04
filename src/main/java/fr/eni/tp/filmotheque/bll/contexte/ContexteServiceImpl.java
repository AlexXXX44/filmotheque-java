package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.stereotype.Service;

@Service("contexteService") // ou @Service si tu n’utilises pas @Qualifier
public class ContexteServiceImpl implements ContexteService {

    private final MembreRepository membreRepository;

    public ContexteServiceImpl(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @Override
    public Membre charger(String email) {
        return membreRepository.findByEmail(email);
    }
}
