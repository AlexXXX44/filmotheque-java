package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

@Entity
public class Avis
//        implements List<Avis>
{

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    private int note;
    private String commentaire;
    @ManyToOne
    private Membre membre;

    public Avis(int id, int note, String commentaire, Film film, Membre membre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.film = film;
        this.membre = membre;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Avis other = (Avis) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Avis de ");
        builder.append(membre);
        builder.append(" - note=");
        builder.append(note);
        builder.append(", commentaire=");
        builder.append(commentaire);
        return builder.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }
}
