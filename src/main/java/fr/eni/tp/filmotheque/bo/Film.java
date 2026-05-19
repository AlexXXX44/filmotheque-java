package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Le titre du film est obligatoire")
    @Size(min = 2, max = 255, message = "Le titre doit faire entre 2 et 255 caractères")
    private String titre; // Ou 'name' selon tes préférences, reste cohérent avec tes getters/setters

    @NotBlank(message = "Le résumé du film est obligatoire")
    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @NotBlank(message = "Le lien du poster est obligatoire")
    private String poster;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être supérieure à 0 minute")
    private Integer duree; // Propriété spécifique aux films !

    @NotNull(message = "L'année de sortie est obligatoire")
    @Min(value = 1888, message = "L'année de sortie doit être supérieure ou égale à 1888") // Le premier film date de 1888
    private Integer annee;

    @ManyToOne
    @JoinColumn(name = "realisateur_id")
    private Participant realisateur;

    @ManyToMany
    @JoinTable(
            name = "film_acteur",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id")
    )
    private List<Participant> acteurs;
    
    @ManyToMany
    @JoinTable( name = "film_genre",
		        joinColumns = @JoinColumn(name = "film_id"), // La colonne pointant vers cette entité (Film)
        	    inverseJoinColumns = @JoinColumn(name = "genre_id") // La colonne pointant vers l'autre entité (Genre)
    )
    private List<Genre> genres = new ArrayList<>();
    @OneToMany(mappedBy = "film")
    private List<Avis> avis;

    public Film() {
        acteurs = new ArrayList<>();
        avis = new ArrayList<>();
    }

    public Film(String titre, int annee, int duree, String affiche, String synopsis, List<Genre> genres) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.affiche = affiche;
        this.synopsis = synopsis;
        this.genres = genres;

        acteurs = new ArrayList<>();
        avis = new ArrayList<>();
    }

    public Film(int id, String titre, int annee, int duree, String synopsis, String affiche, List<Genre> genres) {
        this(titre, annee, duree, synopsis, affiche, genres);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return acteurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Film (");
        builder.append(id);
        builder.append(")\n\ttitre : ");
        builder.append(titre);
        builder.append("[annee : ");
        builder.append(annee);
        builder.append(", duree : ");
        builder.append(duree);
        builder.append(" minutes]\n\tSynopsis : ");
        builder.append(synopsis);
        builder.append("\n\trealisateur : ");
        builder.append(realisateur);
        builder.append("\n\tacteurs : ");
        builder.append(acteurs);
        builder.append("\n\tgenres : ");
        builder.append(genres);
        builder.append("\n\tAvis : ");
        builder.append(avis);
        return builder.toString();
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
        Film other = (Film) obj;
        return id == other.id;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
}
