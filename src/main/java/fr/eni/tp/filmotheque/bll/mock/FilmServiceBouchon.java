package fr.eni.tp.filmotheque.bll.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;

@Service
@Profile("dev")
public class FilmServiceBouchon implements FilmService {
    // Attributs statiques pour gérer les valeurs à afficher et simuler les données
    // en base
    private static List<Film> lstFilms = new ArrayList<>();
    private static List<Genre> lstGenres = new ArrayList<>();
    private static List<Participant> lstParticipants = new ArrayList<>();
    private List<Film> lstTitres = new ArrayList<>();
    private List<Avis> lstAvis = new ArrayList<>();
    private static int indexFilms = 1;
    private static int indexAvis = 2;

    // Représente la table en base de données des genres des films
    private static final String[] genres = {"Animation", "Science-fiction", "Documentaire", "Action", "Comédie",
            "Drame"};

    public FilmServiceBouchon() {
        simulationCoucheDALetDB();
    }

    @Override
    public List<Film> consulterFilms() {
        return lstFilms;
    }

    /**
     * @return film si id correspond
     * @return null si inconnu
     */
    @Override
    public Film consulterFilmParId(long id) {
        return lstFilms.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Genre> consulterGenres() {
        return lstGenres;
    }

    @Override
    public List<Participant> consulterParticipants() {
        return lstParticipants;
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return lstGenres.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    /**
     * @return participant si id correspond
     * @return null si inconnu
     */
    @Override
    public Participant consulterParticipantParId(long id) {
        return lstParticipants.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    @Override
    public void creerFilm(Film film) {
        // Sauvegarde du film
        film.setId(indexFilms++);
        lstFilms.add(film);
    }

    @Override
    public String consulterTitreFilm(long id) {
        FilmService filmService = null;
        Film film = filmService.consulterFilmParId(id);
        if (film != null) {
            return film.getTitre();
        }
        return null;
    }

    @Override
    public List<Avis> consulterAvis(long idFilm) {
        FilmService film = null;
        Film filmSelectionne = film.consulterFilmParId(idFilm);
        return filmSelectionne.getAvis();

    }

    @Override
    public void publierAvis(Avis avis, long idFilm) {
        System.out.println("Film n°" + idFilm + "" + avis);
        // Sauvegarde de l'avis
        avis.setId(indexAvis++);
        lstAvis.add(avis);
    }

    /**
     * Cette méthode permet de simuler le stockage en base de données et la remontée
     * d'information
     */
    public void simulationCoucheDALetDB() {
        // Création de la liste des genres
        for (int index = 0; index < genres.length; index++) {
            lstGenres.add(new Genre(index + 1, genres[index]));
        }

        // Création de la liste des participants aux films (acteurs et réalisateurs)
        // 3 réalisateurs dont 1 pour 2 films et 1 réalisateurs qui est aussi un acteur
        lstParticipants.add(new Participant(1, "Spielberg", "Steven"));
        lstParticipants.add(new Participant(2, "Cronenberg", "David"));
        lstParticipants.add(new Participant(3, "Boon", "Dany"));
        lstParticipants.add(new Participant(16, "Johnston", "Joe"));
        lstParticipants.add(new Participant(17, "Trevorrow", "Colin"));
        lstParticipants.add(new Participant(18, "Bayona", "JA"));
        lstParticipants.add(new Participant(21, "Edwards", "Gareth"));
        lstParticipants.add(new Participant(22, "Zemeckis", "Robert"));

        // 2 acteurs par film et l'un d'eux dans 2 films
        lstParticipants.add(new Participant(4, "Attenborough", "Richard"));
        lstParticipants.add(new Participant(5, "Goldblum", "Jeff"));
        lstParticipants.add(new Participant(6, "Davis", "Geena"));
        lstParticipants.add(new Participant(7, "Rylance", "Mark"));
        lstParticipants.add(new Participant(8, "Barnhill", "Ruby"));
        lstParticipants.add(new Participant(9, "Merad", "Kad"));
        lstParticipants.add(new Participant(10, "Sy", "Omar"));
        lstParticipants.add(new Participant(11, "Moore", "Julianne"));
        lstParticipants.add(new Participant(12, "Neill", "Sam"));
        lstParticipants.add(new Participant(13, "Dern", "Laura"));
        lstParticipants.add(new Participant(14, "Pratt", "Chris"));
        lstParticipants.add(new Participant(15, "Howard", "Bryce Dallas"));
        lstParticipants.add(new Participant(19, "Johansson", "Scarlett"));
        lstParticipants.add(new Participant(20, "Friend", "Rupert"));
        lstParticipants.add(new Participant(23, "J. Fox", "Michael J."));
        lstParticipants.add(new Participant(25, "wilson", "Tom"));
        lstParticipants.add(new Participant(24, "Lloyd", "Christopher"));

        // Création de la liste de films
        // 9 films
        Film jurassicPark = new Film(indexFilms++, "Jurassic Park", 1993, 128,
                "",
                "Le film raconte l'histoire d'un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.");
        jurassicPark.setGenre(lstGenres.get(1));
        jurassicPark.setRealisateur(consulterParticipantParId(1));
        // Associer les acteurs
        jurassicPark.getActeurs().add(consulterParticipantParId(4));
        jurassicPark.getActeurs().add(consulterParticipantParId(5));
        lstTitres.add(jurassicPark);
        lstFilms.add(jurassicPark);

        Film leMondePerdu = new Film(indexFilms++, "Le Monde Perdu", 1997, 120,
                "", "Suite de Jurassic Park, 4 ans après");
        leMondePerdu.setGenre(lstGenres.get(1));
        leMondePerdu.setRealisateur(consulterParticipantParId(1));
        // Associer les acteurs
        leMondePerdu.getActeurs().add(consulterParticipantParId(5));
        leMondePerdu.getActeurs().add(consulterParticipantParId(11));
        lstTitres.add(leMondePerdu);
        lstFilms.add(leMondePerdu);

        Film jurassicPark3 = new Film(indexFilms++, "Jurassic Park 3", 2003, 128,
                "", "Suite de Jurassic Park, 8 ans après");
        jurassicPark3.setGenre(lstGenres.get(1));
        jurassicPark3.setRealisateur(consulterParticipantParId(16));
        // Associer les acteurs
        jurassicPark3.getActeurs().add(consulterParticipantParId(12));
        jurassicPark3.getActeurs().add(consulterParticipantParId(13));
        lstTitres.add(jurassicPark3);
        lstFilms.add(jurassicPark3);

        Film jurassicWorld = new Film(indexFilms++, "Jurassic World", 2015, 120,
                "", "Suite de Jurassic Park");
        jurassicWorld.setGenre(lstGenres.get(1));
        jurassicWorld.setRealisateur(consulterParticipantParId(17));
        // Associer les acteurs
        jurassicWorld.getActeurs().add(consulterParticipantParId(10));
        jurassicWorld.getActeurs().add(consulterParticipantParId(14));
        jurassicWorld.getActeurs().add(consulterParticipantParId(15));
        lstTitres.add(jurassicWorld);
        lstFilms.add(jurassicWorld);

        Film fallenKingdom = new Film(indexFilms++, "Jurassic World : Fallen Kingdom", 2018, 120,
                "",
                "Suite de Jurassic World");
        fallenKingdom.setGenre(lstGenres.get(1));
        fallenKingdom.setRealisateur(consulterParticipantParId(18));
        // Associer les acteurs
        fallenKingdom.getActeurs().add(consulterParticipantParId(5));
        fallenKingdom.getActeurs().add(consulterParticipantParId(14));
        fallenKingdom.getActeurs().add(consulterParticipantParId(15));
        lstTitres.add(fallenKingdom);
        lstFilms.add(fallenKingdom);

        Film renaissance = new Film(indexFilms++, "Jurassic World : Renaissance", 2025, 120,
                "",
                "Suite de Jurassic World avec de nouveaux acteurs");
        renaissance.setGenre(lstGenres.get(1));
        renaissance.setRealisateur(consulterParticipantParId(19));
        // Associer les acteurs
        renaissance.getActeurs().add(consulterParticipantParId(20));
        renaissance.getActeurs().add(consulterParticipantParId(21));
        lstTitres.add(renaissance);
        lstFilms.add(renaissance);

        Film leMondeDapres = new Film(indexFilms++, "Jurassic World : Le Monde D'après", 2022, 120,
                "",
                "Suite de Jurassic World Fallen Kingdom");
        leMondeDapres.setGenre(lstGenres.get(1));
        leMondeDapres.setRealisateur(consulterParticipantParId(17));
        // Associer les acteurs
        leMondeDapres.getActeurs().add(consulterParticipantParId(5));
        leMondeDapres.getActeurs().add(consulterParticipantParId(12));
        leMondeDapres.getActeurs().add(consulterParticipantParId(13));
        leMondeDapres.getActeurs().add(consulterParticipantParId(10));
        leMondeDapres.getActeurs().add(consulterParticipantParId(14));
        leMondeDapres.getActeurs().add(consulterParticipantParId(15));
        lstTitres.add(leMondeDapres);
        lstFilms.add(leMondeDapres);

        Film theFly = new Film(indexFilms++, "The Fly", 1986, 95,
                "Il s'agit de l'adaptation cinématographique de la nouvelle éponyme de l'auteur George Langelaan.",
                        "la_mouche.png");
        theFly.setGenre(lstGenres.get(1));
        theFly.setRealisateur(consulterParticipantParId(2));
        // Associer les acteurs
        theFly.getActeurs().add(consulterParticipantParId(5));
        theFly.getActeurs().add(consulterParticipantParId(6));
        lstTitres.add(theFly);
        lstFilms.add(theFly);

        Film theBFG = new Film(indexFilms++, "The BFG", 2016, 117,
                "Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.",
                        "le_BGG.png");
        theBFG.setGenre(lstGenres.get(4));
        theBFG.setRealisateur(consulterParticipantParId(1));
        // Associer les acteurs
        theBFG.getActeurs().add(consulterParticipantParId(7));
        theBFG.getActeurs().add(consulterParticipantParId(8));
        lstTitres.add(theBFG);
        lstFilms.add(theBFG);

        Film bienvenueChezLesChtis = new Film(indexFilms++, "Bienvenue chez les Ch'tis", 2008, 106,
                "Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d'obtenir une mutation sur la Côte d'Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.",
                        "bienvenue_chez_les_chtis.png");
        bienvenueChezLesChtis.setGenre(lstGenres.get(4));
        bienvenueChezLesChtis.setRealisateur(consulterParticipantParId(3));
        // Associer les acteurs
        bienvenueChezLesChtis.getActeurs().add(consulterParticipantParId(3));
        bienvenueChezLesChtis.getActeurs().add(consulterParticipantParId(9));
        lstTitres.add(bienvenueChezLesChtis);
        lstFilms.add(bienvenueChezLesChtis);

        // Création d'un membre et un avis
        Membre membre1 = new Membre(1, "Baille", "Anne-Lise", "abaille@campus-eni.fr", null);
        Avis avis = new Avis(1, 4, "On rit du début à la fin",bienvenueChezLesChtis, membre1);
        Avis avis2 = new Avis(2, 5, "Drôle du début à la fin",bienvenueChezLesChtis, membre1);
        bienvenueChezLesChtis.getAvis().add(avis);
        bienvenueChezLesChtis.getAvis().add(avis2);

        Film partie1 = new Film(indexFilms++, "Retour vers le futur", 0, 0,
                "Oeuvre originale", "retour vers le futur");
        partie1.setGenre(lstGenres.get(1));
        partie1.setRealisateur(consulterParticipantParId(22));
        //Associer les acteurs
        partie1.getActeurs().add(consulterParticipantParId(23));
        partie1.getActeurs().add(consulterParticipantParId(25));
        partie1.getActeurs().add(consulterParticipantParId(1));
        lstTitres.add(partie1);
        lstFilms.add(partie1);

        Film partie2 = new Film(indexFilms++, "Retour vers le futur Partie 2", 0, 0,
                "Suite de l'original", "retour_vers_le_futur.png");
        partie2.setGenre(lstGenres.get(1));
        partie2.setRealisateur(consulterParticipantParId(22));
        //Associer les acteurs
        partie2.getActeurs().add(consulterParticipantParId(23));
        partie2.getActeurs().add(consulterParticipantParId(25));
        lstTitres.add(partie2);
        lstFilms.add(partie2);

        Film partie3 = new Film(indexFilms++, "Retour vers le futur Partie 3", 0, 0,
                "Suite et fin de la trilogie", "retour_vers_le_futur_part3.png");
        partie3.setGenre(lstGenres.get(1));
        partie3.setRealisateur(consulterParticipantParId(22));
//        Associer les acteurs
        partie3.getActeurs().add(consulterParticipantParId(23));
        partie3.getActeurs().add(consulterParticipantParId(25));
        lstTitres.add(partie3);
        lstFilms.add(partie3);
    }
}
