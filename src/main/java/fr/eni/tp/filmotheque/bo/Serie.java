package fr.eni.tp.filmotheque.bo;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Serie extends Season {

    // Getters et Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @NotBlank(message = "Veuillez entrer un nom ou un titre pour votre série!")
    @Size(min = 2, max = 255)
    private String name;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String overview;

    @NotNull
    @Pattern(regexp = "Canceled|Ended|Returning Series")
    @Column(length = 50)
    private String status;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(precision = 3, scale = 1)
    private BigDecimal vote;

    @Column(precision = 6, scale = 2)
    private BigDecimal popularity;

    private String genres;

    private LocalDateTime firstAirDate;

    private LocalDateTime lastAirDate;

    private String backdrop;

    private String poster;

    private Integer tmdbId;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons;

    // Constructeur par défaut
    public Serie() {
    }

    public Serie setName(String name) {
        this.name = name;
        return this;
    }

    public Serie setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Serie setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getVote() {
        return vote;
    }

    public Serie setVote(BigDecimal vote) {
        this.vote = vote;
        return this;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public Serie setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
        return this;
    }

    public String getGenres() {
        return genres;
    }

    public Serie setGenres(String genres) {
        this.genres = genres;
        return this;
    }

    public LocalDateTime getFirstAirDate() {
        return firstAirDate;
    }

    public Serie setFirstAirDate(LocalDateTime firstAirDate) {
        this.firstAirDate = firstAirDate;
        return this;
    }

    public LocalDateTime getLastAirDate() {
        return lastAirDate;
    }

    public Serie setLastAirDate(LocalDateTime lastAirDate) {
        this.lastAirDate = lastAirDate;
        return this;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public Serie setBackdrop(String backdrop) {
        this.backdrop = backdrop;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public Serie setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public Serie setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Serie setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public Serie setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public Collection<Season> getSeasons() {
        return seasons;
    }

    public Serie addSeason(Season season) {
        if (!this.seasons.contains(season)) {
            this.seasons.add(season);
            season.setSerie(this);
        }
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
}
