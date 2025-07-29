package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;

@Entity
public class Participant extends Personne {

	public Participant(Long o, String spielberg, String steven) {
	}

	public Participant(String nom, String prenom) {
		super(nom, prenom);
	}

	public Participant(long id, String nom, String prenom) {
		super(id, nom, prenom);
	}

	public Participant() {

	}
}
