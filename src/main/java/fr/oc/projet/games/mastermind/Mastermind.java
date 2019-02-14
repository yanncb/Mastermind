package fr.oc.projet.games.mastermind;

import fr.oc.projet.games.Jeu;
import fr.oc.projet3.launcher.Constante;
import fr.oc.projet3.launcher.Utilitaire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class Mastermind extends Jeu {

    private static final Logger logger = LogManager.getLogger(Mastermind.class);

    /**
     * Constructeur vide qui permet d'appeler MasterMind sans lui donner de param.
     */
    public Mastermind() {

    }

    /**
     * Permet de lancer le jeu et d'en selectionner un mode!.
     */
    public void jouer() {
        logger.info("Vous avez  : {} d'essais et {} cases à trouver", getNombreDessais(), getNombreDeChiffre());
        // switch case pour les modes simple, Defenseur et duel.
        switch (getModeDeJeu()) {
            case CHALLENGER: {
                jouerMastermindChallenger();
                break;
            }
            case DEFENDER: {
                jouerMastermindDefenseur();
                break;
            }
            case DUEL: {
                jouerMastermindDuel();
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
    }

    /**
     * Methode qui permet de lancer le jeu en mode Mastermind simple
     */
    private void jouerMastermindChallenger() {  // terminé !!
        logger.info("Vous êtes en mode : Mastermind vous devez tentez de deviner un code que l'ordinateur va generer !");
        int[] random = Utilitaire.creationDuRandom(getDevMod());
        int compteur = 0;
        int[] reponse = new int[2];
        boolean trouve = false;
        int nbEssais = getNombreDessais();
        do {
            // rajouter boucle for plutot que do while.
            logger.info("\nEssai n° {} /  {}   :", (compteur + 1), nbEssais);
            int[] saisieClavier = SaisieClavier();

            trouve = compareSaisieEtCodeSecret(random, saisieClavier, reponse);
            logger.info("Proposition : {} -> Réponse : {} présent, {} bien placés.", saisieClavier, reponse[0], reponse[1]);
            compteur++;
        } while (!trouve && compteur <= nbEssais);
        if (trouve) {
            logger.info("Bravo !!! Tu gagne en {} essais ", compteur);
        }
        if (!trouve && compteur == nbEssais) {
            logger.info("Tu as PERDU !!! tu as atteint tes {} essais ", compteur);
        }

    }

    /**
     * Methode qui permet de lancer le jeu en mode Mastermind Defenseur
     */
    private void jouerMastermindDefenseur() {

        int lenght = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int compteurOrdi = 0;
        int[] reponse = new int[lenght];
        boolean trouve = false;
        String vainqueur = null;
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", lenght);
        int[] codeSecretSaisieParUtilisateur = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);

        do {

            logger.info("\nEssai n° {} /  {} de l'{} :", Constante.IA, (compteurOrdi + 1), getNombreDessais());
            int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
            trouve = compareSaisieEtCodeSecret(codeSecretSaisieParUtilisateur, propositionDeLordinateur, reponse);
            logger.info("Proposition {} : {} -> Reponse : {} present, {} bien places.", Constante.IA, propositionDeLordinateur, reponse[0], reponse[1]);
            compteurOrdi++;
            if (trouve) {
                vainqueur = Constante.IA;
            }
        } while (!trouve && nbEssais != compteurOrdi);

        if (compteurOrdi == nbEssais && !trouve) {
            logger.info("L'{} à perdu. Essai {} atteint.", Constante.IA, compteurOrdi);
        }
        if (trouve) {
            logger.info("Bravo !!! {} a gagné en {} essais", vainqueur, compteurOrdi);
        }
    }

    /**
     * Methode qui permet de lancer le jeu en mode Mastermind Challenger
     */
    private void jouerMastermindDuel() {  // ok

        int lenght = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int[] codeGenereParLordi = Utilitaire.creationDuRandom(getDevMod()); // recuperation du random dans la methode creationduRandom dans utilitaire, et le met dans un tableau codeGenereParLordi
        int compteur = 0;
        int compteurOrdi = 0;
        int[] reponse = new int[lenght];
        boolean trouve = false;
        boolean premierJouerEstUtilisateur = true;
        String vainqueur = null;
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", lenght);
        int[] codeSecretSaisieParUtilisateur = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);
        int[] codeTapeParUtilisateur;

        do {

            if (premierJouerEstUtilisateur) {
                logger.info("\nEssai n° {} /  {}   :", (compteur + 1), getNombreDessais());
                logger.info("Combinaison secrete de {} {}", Constante.IA, codeGenereParLordi);
                logger.info("Saisissez votre combinaison");
                codeTapeParUtilisateur = SaisieClavier();

                trouve = compareSaisieEtCodeSecret(codeGenereParLordi, codeTapeParUtilisateur, reponse);
                logger.info("Proposition : {} -> Réponse : {} présent, {} bien placés.", codeTapeParUtilisateur, reponse[0], reponse[1]);
                compteur++;
                if (trouve) {
                    vainqueur = "Le joueur";

                }
                premierJouerEstUtilisateur = false;
            } else {
                logger.info("\nEssai n° {} /  {} de l'{} :", Constante.IA, (compteurOrdi + 1), getNombreDessais());
                logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);
                int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
                trouve = compareSaisieEtCodeSecret(codeSecretSaisieParUtilisateur, propositionDeLordinateur, reponse);
                logger.info("Proposition {} : {} -> Reponse : {} present, {} bien places.", Constante.IA, propositionDeLordinateur, reponse[0], reponse[1]);
                compteurOrdi++;
                if (trouve) {
                    vainqueur = Constante.IA;
                }
                premierJouerEstUtilisateur = true;
            }

        } while (!trouve && nbEssais != compteur);

        if (compteur == nbEssais && !trouve) {
            logger.info("L'{} à perdu. Essai {} atteint.", Constante.IA, compteur);
        }
        if (trouve) {
            logger.info("Bravo !!! {} a gagné en {} essais", vainqueur, compteur);
        }
    }

    /**
     * Retourne vrai si le nombre nombreRecherche existe dans le tableau, Sinon retourne faux.
     * @param nombreRecherche Nombre recherché dans le tableau
     * @param tableau         Tableau dans lequel on cherche le nombre
     * @return
     */
    private static boolean existDansLeTableau(int nombreRecherche, int[] tableau) {
        return Arrays.stream(tableau).anyMatch(i -> i == nombreRecherche); //refacto i -> i dans le tab nombreRecherche est ce qu'il y a une valeur qui correspond, si oui renvoie true, sinon renvoie false.
    }

    /**
     * Méthode pour comparer le code secret et la saisie du joueur.
     *
     * @param codeSecret    : code secret à deviner
     * @param codeSaisie : codeSaisie à comparer
     * @param reponse       : retourne le nombre de bonne places et présent.
     * @return boolean indiquant si la saisie clavier correspond au code secret à deviner.
     */

    public boolean compareSaisieEtCodeSecret(int[] codeSecret, int[] codeSaisie, int[] reponse) {
        int nbPresent = 0;
        int nbBonnePlace = 0;
        int compteur = codeSecret.length;
        for (int i = 0; i < compteur; i++) {
            int chiffreCourant = codeSaisie[i];
            boolean estALaBonnePlace = false;
            boolean existDansLeTableau = existDansLeTableau(chiffreCourant, codeSecret);
            if (chiffreCourant == codeSecret[i]) {
                estALaBonnePlace = true;
                nbBonnePlace++;
            }
            if (existDansLeTableau && !estALaBonnePlace) {
                nbPresent++;
            }
        }
        reponse[0] = nbPresent;
        reponse[1] = nbBonnePlace;
        return (nbBonnePlace == compteur);
    }
}








