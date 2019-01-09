package fr.oc.projet.games.mastermind;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.games.Jeu;
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
     * Constructeur qui permet de construire le MasterMind
     *
     * @param user          nom d'utilisateur
     * @param modeDeJeu     1/2/3
     * @param resultat      boolean gagné ou perdu
     * @param nombredessais pour limiter le nombres d'essai il faut les compter
     * @param description   description du mode de jeu.
     */
    public Mastermind(String user, EnumModeDeJeux modeDeJeu, int resultat, int nombredessais, String description, boolean devmod, int nombreChiffre) {
        super(user, modeDeJeu, resultat, nombredessais, description, devmod, nombreChiffre);
    }

    /**
     * Permet de lancer le jeu et d'en selectionner un mode!.
     */
    public void jouer() {
        logger.info("Vous avez  : {} d'essais et {} cases à trouver", getNombreDessais(), getNombreDeChiffre());
        // switch case pour les modes simple, Defenseur et duel.
        switch (getModeDeJeu().getCode()) {
            case 1: {
                jouerMastermindChallenger();
                break;
            }
            case 2: {
                jouerMastermindDefenseur();
                break;
            }
            case 3: {
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

            logger.info("\nEssai n° {} /  {}   :", (compteur + 1), nbEssais);
            int[] saisieClavier = SaisieClavier();

            trouve = compareSaisieEtCodeSecret(random, saisieClavier, reponse);
            logger.info("Proposition : {} -> Réponse : {} présent, {} bien placés.", saisieClavier, reponse[0], reponse[1]);
            compteur++;
        } while (!trouve && compteur != nbEssais);
       if (trouve) {
           logger.info("Bravo !!! Tu gagne en {} essais ", compteur);
       }
       if (!trouve && compteur==nbEssais){
            logger.info("Tu as PERDU !!! tu as atteint tes {} essais ", compteur);
        }

    }


    /**
     * Methode qui permet de lancer le jeu en mode Mastermind Challenger
     */
    private void jouerMastermindDuel() {
        // logger.info("Vous êtes en mode : Mastermind vous devez tentez de deviner un code que l'ordinateur va generer !");
        // Combinaison creer par l'ordinateur pour trouver mon code.
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", getNombreDeChiffre());
        int[] resultatATrouverParOrdi = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", resultatATrouverParOrdi);
        int[] codeGenereParLordi = Utilitaire.creationDuRandom(getDevMod());
        int compteur = 0;
        int[] reponse = new int[getNombreDeChiffre()];
        int[] bonChiffreAenleverDuRamdom = new int[getNombreDeChiffre()];

        do {

//            while (!bonChiffreAenleverDuRamdom.equals(resultatATrouverParOrdi)) {
            logger.info("\nEssai n° {} /  {}   :", (compteur + 1), getNombreDessais());
            compareSaisieEtCodeSecret(codeGenereParLordi, resultatATrouverParOrdi, reponse);
//            }
            compteur++;
            logger.info("Proposition : {} -> Réponse : {} présent, {} bien placés.", bonChiffreAenleverDuRamdom, reponse[0], reponse[1]);
        } while (getNombreDeChiffre() != (reponse[1]) && getNombreDessais() != (compteur));
        if (compteur >= getNombreDessais()) {
            logger.info("L'ordi a perdu !!! en {} essais,  Tu as donc Gagné !! ", compteur);
        } else {
            logger.info("L'ordi Gagne en {} essais, et toi tu perds !!! ", compteur);
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
        int[] recherche = new int[getNombreDeChiffre()];
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
        }  // faire le nouveau random ici en fonction du nombre de chiffre a la bonne place- present.
        reponse[0] = nbPresent;
        reponse[1] = nbBonnePlace;
        return (nbBonnePlace == compteur);
    }
}








