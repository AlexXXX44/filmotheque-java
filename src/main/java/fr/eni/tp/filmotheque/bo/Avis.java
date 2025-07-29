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
public class Avis implements List<Avis> {

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @Id
    private long id;
    private int note;
    private String commentaire;
    @ManyToOne
    private Membre membre;

    public Avis(long id, int note, String commentaire, Film film, Membre membre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.film = film;
        this.membre = membre;
    }

    public Avis(int id, String commentaire, Film film, Membre membre) {
        this.id = id;
        this.commentaire = commentaire;
        this.film = film;
        this.membre = membre;
    }

    public Avis(int id, int i, String s, Membre membre1) {

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

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<Avis> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(Avis e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Avis> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Avis> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Avis get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Avis set(int index, Avis element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(int index, Avis element) {
        // TODO Auto-generated method stub

    }

    @Override
    public Avis remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<Avis> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<Avis> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Avis> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setFilm(Film film) {
        return;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
