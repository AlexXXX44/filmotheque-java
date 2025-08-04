package fr.eni.tp.filmotheque.bo;

import fr.eni.tp.filmotheque.bo.Serie;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private Date $firstAirDate;

    @Column(columnDefinition = "TEXT")
    private String overview;

//    @Size(max = 255)
    private String poster;

    private int tmdbId;

    private Date dateCreated;

    private Date dateModified;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
