package fr.oc.projet.games.recherchePlusMoins;

import fr.oc.projet.games.Jeu;
import fr.oc.projet3.launcher.Constante;
import fr.oc.projet3.launcher.Utilitaire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class RecherchePlusMoins extends Jeu {

    private static final Logger logger = LogManager.getLogger(RecherchePlusMoins.class);

    /**
     * Constructeur vide qui permet d'appeler RecherchePlusMoins sans lui donner de paramètre.
     */
    public RecherchePlusMoins() {
    }

    /**
     * Permet de lancer le jeu et d'en selectionner un mode!.
     */
    public void jouer() {
        logger.info("Vous avez  : {} éssais et {} cases à trouver", getNombreDessais(), getNombreDeChiffre());
        switch (getModeDeJeu()) {
            case CHALLENGER: {
                jouerRecherchePlusMoinsChallenger();
                break;
            }
            case DEFENDER: {
                jouerRecherchePlusMoinsDefenseur();
                break;
            }
            case DUEL: {
                jouerRecherchePlusMoinsDuel();
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
    }

    /**
     * Methode qui permet de lancer le jeu en mode Challenger
     */
    private void jouerRecherchePlusMoinsChallenger() {

        logger.info("Vous êtes en mode : Challenger vous devez tentez de deviner un code que l'ordinateur va generer !");
        int[] random = Utilitaire.creationDuRandom(getDevMod());
        int compteur = 0;
        boolean trouve = false;
        int nbEssais = getNombreDessais();
        int longueur = getNombreDeChiffre();
        String[] tabPlusOuMoins = new String[longueur];

        do {

            logger.info("Essai n° {} /  {}   :", (compteur + 1), nbEssais);
            int[] saisieClavier = SaisieClavier();
            trouve = comparerCodeSecretAvecPlusOuMoins(saisieClavier, random, tabPlusOuMoins);

            logger.info("Proposition : {} -> Réponse : {}.", saisieClavier, formatterTableau(tabPlusOuMoins));
            compteur++;

        } while (!trouve && compteur != nbEssais);
        if (trouve) {
            logger.info("Bravo !!! Tu gagne en {} essais ", compteur);
        }
        if (!trouve && compteur == nbEssais) {
            logger.info("Tu as PERDU !!! tu as atteint tes {} essais ", compteur);
        }

    }

    /**
     * Methode qui permet de lancer le jeu en mode Defenseur
     */
    private void jouerRecherchePlusMoinsDefenseur() {

        int longueur = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int compteurIA = 0;
        boolean trouve = false;
        String vainqueur = null;
        String[] tabPlusOuMoins = new String[longueur];
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", longueur);
        int[] codeSecretSaisieParUtilisateur = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);

        do {

            logger.info("Essai n° {} /  {} de l'{} :", Constante.IA, (compteurIA + 1), getNombreDessais());
            int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
            trouve = comparerCodeSecretAvecPlusOuMoins(propositionDeLordinateur, codeSecretSaisieParUtilisateur, tabPlusOuMoins);
            logger.info("Proposition de l'{} : {} -> Reponse : {} .", Constante.IA, propositionDeLordinateur, formatterTableau(tabPlusOuMoins));
            compteurIA++;
            if (trouve) {
                vainqueur = Constante.IA;
            }
        } while (!trouve && nbEssais != compteurIA);

        if (compteurIA == nbEssais && !trouve) {
            logger.info("L'{} à perdu. Essai {} atteint.", Constante.IA, compteurIA);
        }
        if (trouve) {
            logger.info("Bravo !!! {} a gagné en {} essais", vainqueur, compteurIA);
        }
    }

    /**
     * Methode qui permet de lancer le jeu en mode Duel
     */
    private void jouerRecherchePlusMoinsDuel() {

        int longueur = getNombreDeChiffre();
        int nbEssais = getNombreDessais() * 2;
        int[] codeGenereParLordi = Utilitaire.creationDuRandom(getDevMod()); // Virgule dans resultat
        int compteurUtilisateur = 0;
        int compteurIA = 0;
        int compteurTotal = 0;
        boolean trouve = false;
        boolean premierJouerEstUtilisateur = true;
        String vainqueur = null;
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", longueur);
        int[] codeSecretSaisieParUtilisateur = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);
        int[] codeTapeParUtilisateur;
        String[] tabPlusOuMoins = new String[longueur];

        do {

            if (premierJouerEstUtilisateur) {
                logger.info("Essai n° {} /  {}   :", (compteurUtilisateur + 1), getNombreDessais());
                logger.info("Combinaison secrete de {} {}", Constante.IA, codeGenereParLordi);
                logger.info("Saisissez votre combinaison");
                codeTapeParUtilisateur = SaisieClavier();

                trouve = comparerCodeSecretAvecPlusOuMoins(codeTapeParUtilisateur, codeGenereParLordi, tabPlusOuMoins);
                logger.info("Proposition : {} -> Réponse : {}.", codeTapeParUtilisateur, formatterTableau(tabPlusOuMoins));
                compteurUtilisateur++;
                if (trouve) {
                    vainqueur = "Le joueur";

                }
                premierJouerEstUtilisateur = false;
            } else {
                logger.info("\nEssai n° {} /  {} de l'{} :", Constante.IA, (compteurIA + 1), getNombreDessais());
                logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);
                int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
                trouve = comparerCodeSecretAvecPlusOuMoins(propositionDeLordinateur, codeSecretSaisieParUtilisateur, tabPlusOuMoins);
                logger.info("Proposition {} : {} -> Reponse : {}.", Constante.IA, propositionDeLordinateur, formatterTableau(tabPlusOuMoins));
                compteurIA++;
                if (trouve) {
                    vainqueur = Constante.IA;
                }
                premierJouerEstUtilisateur = true;
            }
            compteurTotal++;

        } while (!trouve && nbEssais != compteurTotal);

        if (compteurTotal == nbEssais && !trouve) {
            logger.info("L'{} à perdu. Essai {} atteint.", Constante.IA, compteurUtilisateur);
        }
        if (trouve) {
            logger.info("Bravo !!! {} a gagné en {} essais", vainqueur, compteurUtilisateur);
        }
    }

    /**
     * TODO : expliquer la méthode
     *
     * @param codeSaisie   permet de recuperer le code et de le comparer avec le code secret à decouvrir
     * @param codeSecret   code secret à decouvrir
     * @param tabPlusMoins le tableau permet de renvoyer un affichage de plus et de moins pour pouvoir jouer
     * @return sert juste à effectuer la vérification pour la victoire.
     */

    public boolean comparerCodeSecretAvecPlusOuMoins(int[] codeSaisie, int[] codeSecret, String[] tabPlusMoins) {
        int compteur = 0;
        for (int i = 0; i < codeSaisie.length; i++) {

            if (codeSaisie[i] < codeSecret[i]) {
                tabPlusMoins[i] = "+";
            } else if (codeSaisie[i] > codeSecret[i]) {
                tabPlusMoins[i] = "-";
            } else {
                tabPlusMoins[i] = "=";
                compteur++;
            }
        }

        if (compteur == codeSaisie.length) {
            return true;
        } else {
            return false;
        }
    }

    private String formatterTableau(String[] tabPlusMoins) {
        return Arrays.asList(tabPlusMoins).toString().replace(",", "").replace(" ", "");
    }
}
