package fr.eni.tp.filmotheque.init;

import fr.eni.tp.filmotheque.bo.*;
import fr.eni.tp.filmotheque.dal.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class InitialiseurDonnees {

    @Bean
    CommandLineRunner initDatabase(
            GenreRepository genreRepository,
            ParticipantRepository participantRepository,
            FilmRepository filmRepository,
            MembreRepository membreRepository,
            AvisRepository avisRepository
    ) {
        return args -> {
            // --- GENRES ---
            List<Genre> genres = Arrays.asList(
                    new Genre("Animation"),
                    new Genre("Science-fiction"),
                    new Genre("Documentaire"),
                    new Genre("Action"),
                    new Genre("Comédie"),
                    new Genre("Drame")
            );
            genreRepository.saveAll(genres);

            Map<String, Genre> genreMap = genres.stream()
                    .collect(java.util.stream.Collectors.toMap(Genre::getTitre, g -> g));

            // --- PARTICIPANTS ---
            Participant spielberg = participantRepository.save(new Participant("Spielberg", "Steven"));
            Participant cronenberg = participantRepository.save(new Participant("Cronenberg", "David"));
            Participant boon = participantRepository.save(new Participant("Boon", "Dany"));
            Participant johnston = participantRepository.save(new Participant("Johnston", "Joe"));
            Participant trevorrow = participantRepository.save(new Participant("Trevorrow", "Colin"));
            Participant bayona = participantRepository.save(new Participant("Bayona", "JA"));
            Participant edwards = participantRepository.save(new Participant("Edwards", "Gareth"));
            Participant zemeckis = participantRepository.save(new Participant("Zemeckis", "Robert"));

            Participant attenborough = participantRepository.save(new Participant("Attenborough", "Richard"));
            Participant goldblum = participantRepository.save(new Participant("Goldblum", "Jeff"));
            Participant davis = participantRepository.save(new Participant("Davis", "Geena"));
            Participant rylance = participantRepository.save(new Participant("Rylance", "Mark"));
            Participant barnhill = participantRepository.save(new Participant("Barnhill", "Ruby"));
            Participant merad = participantRepository.save(new Participant("Merad", "Kad"));
            Participant sy = participantRepository.save(new Participant("Sy", "Omar"));
            Participant moore = participantRepository.save(new Participant("Moore", "Julianne"));
            Participant neill = participantRepository.save(new Participant("Neill", "Sam"));
            Participant dern = participantRepository.save(new Participant("Dern", "Laura"));
            Participant pratt = participantRepository.save(new Participant("Pratt", "Chris"));
            Participant howard = participantRepository.save(new Participant("Howard", "Bryce Dallas"));
            Participant johansson = participantRepository.save(new Participant("Johansson", "Scarlett"));
            Participant friend = participantRepository.save(new Participant("Friend", "Rupert"));
            Participant fox = participantRepository.save(new Participant("J. Fox", "Michael J."));
            Participant lloyd = participantRepository.save(new Participant("Lloyd", "Christopher"));
            Participant wilson = participantRepository.save(new Participant("wilson", "Tom"));

            // --- MEMBRE ---
            Membre membre = membreRepository.save(new Membre(1, "Baille", "Anne-Lise", "abaille@campus-eni.fr", "motdepasse"));

            // --- FILMS ---
            filmRepository.saveAll(List.of(
                    new Film("Jurassic Park", 1993, 128, "jurassic.png",
                            "Le film raconte l'histoire d'un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage."
                    ),
                    new Film("Le Monde Perdu", 1997, 120, null,
                            "Suite de Jurassic Park, 4 ans après"
                    ),
                    new Film("Jurassic Park 3", 2003, 128, null,
                            "Suite de Jurassic Park, 8 ans après"
                    ),
                    new Film("Jurassic World", 2015, 120, null,
                            "Suite de Jurassic Park"
                    ),
                    new Film("Jurassic World : Fallen Kingdom", 2018, 120, null,
                            "Suite de Jurassic World"
                    ),
                    new Film("Jurassic World : Renaissance", 2025, 120, null,
                            "Suite de Jurassic World avec de nouveaux acteurs"
                    ),
                    new Film("Jurassic World : Le Monde D'après", 2022, 120, null,
                            "Suite de Jurassic World Fallen Kingdom"
                    ),
                    new Film("The Fly", 1986, 95, "la_mouche.png",
                            "Il s'agit de l'adaptation cinématographique de la nouvelle éponyme de l'auteur George Langelaan."
                    ),
                    new Film("Le BGG", 2016, 117, null,
                            "Un géant débonnaire enlève une jeune orpheline pour l’emmener dans son monde des rêves"
                    ),
                    new Film("La Guerre des Boutons", 2011, 109, null,
                            "Deux bandes de gamins de villages rivaux s'affrontent à coups de boutons arrachés."
                    ),
                    new Film("Rogue One", 2016, 133, null,
                            "Un groupe de résistants s'unissent pour voler les plans de l'Étoile de la Mort."
                    ),
                    new Film("Retour vers le futur", 1985, 116, "retour.png",
                            "Marty et Doc retournent dans le passé à l'aide d'une DeLorean modifiée."
                    ),
                    new Film("Seul au monde", 2000, 144, null,
                            "Un homme échoué sur une île déserte doit survivre seul."
                    )
            ));

            // --- AVIS ---
            List<Film> allFilms = filmRepository.findAll();
            for (Film f : allFilms) {
                avisRepository.save(new Avis( 4, "Un excellent film", f, membre));
            }
        };
    }
}
