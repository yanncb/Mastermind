package fr.oc.projet.games.mastermind;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.games.Jeu;
import fr.oc.projet3.launcher.Constante;
import fr.oc.projet3.launcher.Utilitaire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;


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
     * Methode qui permet de lancer le jeu en mode Mastermind Challenger
     */
    private void jouerMastermindDuel() {  // 90%


        int lenght = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int[] codeGenereParLordi = Utilitaire.creationDuRandom(getDevMod()); // recuperation du random dans la methode creationduRandom dans utilitaire, et le met dans un tableau codeGenereParLordi
        int compteur = 0;
        int compteurOrdi = 0;
        int[] reponse = new int[lenght];
        int[] bonChiffreAenleverDuRamdom = new int[lenght];
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
                logger.info("Proposition {} : {} -> Reponse : {} present, {} bien places.",Constante.IA, propositionDeLordinateur, reponse[0], reponse[1]);
                compteurOrdi++;
                if (trouve) {
                    vainqueur = Constante.IA;
                }
                premierJouerEstUtilisateur = true;
            }


        } while (!trouve && nbEssais != compteur);

        if (compteur == nbEssais && !trouve) {
            logger.info("L'{} à perdu. Essai {} atteint.",Constante.IA, compteur);
        }
        if (trouve) {
            logger.info("Bravo !!! {} a gagné en {} essais", vainqueur, compteur);
        }
    }

    /**
     * Methode qui permet de lancer le jeu en mode Mastermind Defenseur
     */
    private void jouerMastermindDefenseur() {
        logger.info("Vous êtes en mode : Defenseur et vous devez donc inscrire un code que l'ordinateur vas tenter de deviner");
        int[] random = Utilitaire.creationDuRandom(getDevMod()); //2 L'ordi genere un code de la meme longueur que la longueur defini dans properties
        int[] nombrePourLaSolutionPc = new int[getNombreDeChiffre()];
        logger.info("(Proposition faite par l'ordinateur : {}", random);
        int[] chiffreATrouver = SaisieClavier();  //1 saisie un code  longueur defini par properties.
        int compteur = 0;
        int nbPresent = 0;
        int nbBonnePlace = 0;
        for (int k = 0; k < getNombreDessais(); k++) { //5 l'ordi retape le code jusqu'a "nbretry"

            do { //3 Comparaison des random et code saisie par utilisateur.
                Random nouveauRandom = new Random();
                int[] nouveauRandomm = new int[getNombreDeChiffre()];
                for (int f = 0; f < getNombreDeChiffre(); f++) {
                    nouveauRandomm[f] = nouveauRandom.nextInt(getNombreDeChiffre() - nbBonnePlace);
                }

                if (compteur != 0) {
                    random = nombrePourLaSolutionPc;
                }
                logger.info("(Proposition faite par l'ordinateur : {}", random);
//            int[] chiffreATrouver = saisieClavier();  //1 saisie un code defini par properties.
                for (int i = 0; i < getNombreDeChiffre(); i++) {
                    int chiffreCourant = random[i];
                    boolean estALaBonnePlace = false;

                    boolean existeDansLeTableau = existDansLeTableau(chiffreCourant, chiffreATrouver);

                    if (chiffreCourant == chiffreATrouver[i]) {
                        estALaBonnePlace = true;
                    }

                    if (existeDansLeTableau && !estALaBonnePlace) {
                        nbPresent++;
                    }
                    if (estALaBonnePlace) {
                        nbBonnePlace++;
                        nombrePourLaSolutionPc[i] = random[i];  // 4 recup de la bonne valeur et mise dans tableau à la valeur de i.

                    }
                    if (!existeDansLeTableau) {
                        int[] nbZero = new int[getNombreDeChiffre()];

                        nombrePourLaSolutionPc[i] = nbZero[i];

                    }
                }

                logger.info("\nEssai n° {} /  {}   :", (compteur + 1), getNombreDessais());
                compteur++;
                logger.info("Proposition : {} -> Réponse : {} présent, {} bien placés.", chiffreATrouver, nbPresent, nbBonnePlace);
                nbPresent = 0;
                nbBonnePlace = 0;
            } while (getNombreDeChiffre() != (nbBonnePlace) && getNombreDessais() != (compteur));
            if (compteur == getNombreDessais()) {
                logger.info("Bravo ta combinaison n'a pas été trouvée en {} essais ", compteur);
            } else if (nbBonnePlace == getNombreDeChiffre()) {
                logger.info("Désolé l'ordinateur gagne en {} essais ", compteur);
            }
        }
    }


    /**
     * Retourne vrai si le nombre nombreRecherche existe dans le tableau. Sinon retourne faux.
     *
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
     * @param saisieClavier : saisie clavier utilisateur
     * @param reponse       : retourne le nombre de bonne places et présent.
     * @return booleen indiquant si la saisie clavier correspond au code secret à deviner.
     */
    public boolean compareSaisieEtCodeSecret(int[] codeSecret, int[] saisieClavier, int[] reponse) {
        int nbPresent = 0;
        int nbBonnePlace = 0;
        int compteur = codeSecret.length;
        for (int i = 0; i < compteur; i++) {
            int chiffreCourant = saisieClavier[i];
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








