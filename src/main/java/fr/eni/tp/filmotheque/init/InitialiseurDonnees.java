package fr.eni.tp.filmotheque.init;

import fr.eni.tp.filmotheque.bo.*;
import fr.eni.tp.filmotheque.dal.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitialiseurDonnees {
    @Bean
    CommandLineRunner intitDatabase(
            GenreRepository genreRepository,
            ParticipantRepository participantRepository,
            FilmRepository filmRepository,
            MembreRepository membreRepository,
            AvisRepository avisRepository
    ) {
        return args -> {

//        --- GENRES ---
            List<Genre> genres = Arrays.asList(
                    new Genre("Animation"),
                    new Genre("Science-fiction"),
                    new Genre("Documentaire"),
                    new Genre("Action"),
                    new Genre("Comédie"),
                    new Genre("Drame")
            );
            genreRepository.saveAll(genres);

            // --- PARTICIPANTS ---
            Participant spielberg = participantRepository.save(new Participant("Spielberg", "Steven"));
            Participant goldblum = participantRepository.save(new Participant("Goldblum", "Jeff"));
            Participant attenborough = participantRepository.save(new Participant("Attenborough", "Richard"));
            Participant cron = participantRepository.save(new Participant("Cronenberg", "David"));

            // --- MEMBRES ---
            Membre membre = membreRepository.save(new Membre(0, "Baille", "Anne-Lise", "abaille@campus-eni.fr", null));

            // --- FILMS ---
            Film jurassic = new Film(0, "Jurassic Park", 1993, 128,
                    "Le film raconte l'histoire d'un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.",
                    "jurassic.png");

            jurassic.setGenre(genres.get(1)); // Science-fiction
            jurassic.setRealisateur(spielberg);
            jurassic.getActeurs().addAll(List.of(attenborough, goldblum));

            filmRepository.save(jurassic);

            // --- AVIS ---
            avisRepository.save(new Avis( 4, "On rit du début à la fin", jurassic, membre));
            avisRepository.save(new Avis(5, "Drôle du début à la fin", jurassic, membre));
        };
    }
}