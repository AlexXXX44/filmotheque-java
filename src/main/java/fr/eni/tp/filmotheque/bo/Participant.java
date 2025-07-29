package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Participant extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;

	public Participant(String nom, String prenom) {
		super(nom, prenom);
	}

	public Participant(long id, String nom, String prenom) {
		super(id, nom, prenom);
	}

	public Participant() {

	}
}
