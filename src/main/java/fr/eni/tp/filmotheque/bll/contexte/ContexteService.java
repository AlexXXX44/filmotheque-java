package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.stereotype.Service;

@Service
public interface ContexteService {
	Membre charger(String email);
}
