package fr.eni.tp.filmotheque.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import java.util.ArrayList;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Veuillez entrer un nom pour votre série!")
    @Size(min = 2, max = 255)
    private String name;

    @Column(name = "overview", columnDefinition = "TEXT") // "TEXT" car un résumé peut être long
    private String overview;

    @NotNull
    @Pattern(regexp = "Canceled|Ended|Returning Series")
    @Column(name = "status", length = 50)
    private String status;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "vote", precision = 3, scale = 1)
    private BigDecimal vote;

    @Column(name = "popularity", precision = 6, scale = 2)
    private BigDecimal popularity;

    @Column(name = "first_air_date")
    private LocalDateTime firstAirDate;

    @Column(name = "last_air_date")
    private LocalDateTime lastAirDate;

    private String backdrop;

    private String poster;

    private Integer tmdbId;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons;

    @ManyToMany
    @JoinTable(
        name = "serie_genre",
        joinColumns = @JoinColumn(name = "serie_id"), // La colonne pointant vers cette entité (Serie)
        inverseJoinColumns = @JoinColumn(name = "genre_id") // La colonne pointant vers l'autre entité (Genre)
    )
    private List<Genre> genres = new ArrayList<>();

    public List<Genre> getGenres() {
        return genres;
    }

    // Constructeur par défaut
    public Serie() {
    }

    public Serie(String backdrop, LocalDateTime dateCreated, LocalDateTime dateModified, LocalDateTime firstAirDate, List<Genre> genres, Integer id, LocalDateTime lastAirDate, String name, String overview, BigDecimal popularity, String poster, List<Season> seasons, String status, Integer tmdbId, BigDecimal vote) {
        this.backdrop = backdrop;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.name = name;
        this.overview = overview;
        this.popularity = popularity;
        this.poster = poster;
        this.seasons = seasons;
        this.status = status;
        this.tmdbId = tmdbId;
        this.vote = vote;
    }
         
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBackdrop() {
        return this.backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }
    
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public LocalDateTime getFirstAirDate() {
        return this.firstAirDate;
    }

    public LocalDateTime getLastAirDate() {
        return lastAirDate;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getVote() {
        return this.vote;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public Serie setName(String name) {
        this.name = name;
        return this;
    }

    public Serie setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public Serie setStatus(String status) {
        this.status = status;
        return this;
    }

    public Serie setVote(BigDecimal vote) {
        this.vote = vote;
        return this;
    }

    public Serie setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
        return this;
    }

    public Serie setGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    } 

    public Serie setFirstAirDate(LocalDateTime firstAirDate) {
        this.firstAirDate = firstAirDate;
        return this;
    }

    public Serie setLastAirDate(LocalDateTime lastAirDate) {
        this.lastAirDate = lastAirDate;
        return this;
    }

    public Serie setBackdrop(String backdrop) {
        this.backdrop = backdrop;
        return this;
    }

    public Serie setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public Serie setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
        return this;
    }

    public Serie setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Serie setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public Serie removeSeason(Season season) {
        if (this.seasons.remove(season)) {
            if (season.getSerie() == this) {
                season.setSerie(null);
            }
        }
        return this;
    }

    public String getOverview() {
        return this.overview;
    }

}
