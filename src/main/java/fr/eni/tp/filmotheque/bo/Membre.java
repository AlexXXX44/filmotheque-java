package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;

@Entity
public class Membre extends Personne {

	private String email;//	pseudo;
	private String motDePasse;
	private boolean admin;

	public Membre() {
	}

	public Membre(String nom, String prenom, String email, String motDePasse) {
		super(nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
	}

	public Membre(long id, String nom, String prenom, String email, String motDePasse) {
		super(id, nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
        return super.toString() +
                " - Membre (pseudo=" +
                email +
                ", admin=" +
                admin +
                ") ";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
	this.email = mail;
	}
}
