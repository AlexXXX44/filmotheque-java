package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private Date firstAirDate;

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

    public Season(int id, int number, Date firstAirDate, String overview, String poster, int tmdbId, Date dateCreated, Date dateModified, Serie serie) {
        this.id = id;
        this.number = number;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.poster = poster;
        this.tmdbId = tmdbId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.serie = serie;
    }

    public Season() {

    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
