package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Participant extends Personne {

	public Participant(String nom, String prenom) {
		super(nom, prenom);
	}

	public Participant(long id, String nom, String prenom) {
		super(id, nom, prenom);
	}

	public Participant() {

	}
}
