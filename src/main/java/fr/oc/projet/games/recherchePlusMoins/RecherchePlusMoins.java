package fr.oc.projet.games.recherchePlusMoins;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.games.Jeu;
import fr.oc.projet3.launcher.Constante;
import fr.oc.projet3.launcher.Utilitaire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecherchePlusMoins extends Jeu {

    private static final Logger logger = LogManager.getLogger(RecherchePlusMoins.class);

    /**
     * Constructeur vide qui permet d'appeler RecherchePlusMoins sans lui donner de param.
     */
    public RecherchePlusMoins() {
        Utilitaire.creationDuRandom(getDevMod());
    }

    /**
     * Constructeur qui permet de construire le RecherchePlusMoins
     *
     * @param user          nom d'utilisateur
     * @param modeDeJeu     1/2/3
     * @param resultat      int code à decouvrir
     * @param nombredessais pour limiter le nombres d'essai il faut les compter
     * @param description   description du mode de jeu.
     * @param devmod        boolean
     * @param valeursaisie  les valeurs saisie par l'utilisateur ou l'ordi.
     */
    public RecherchePlusMoins(String user, EnumModeDeJeux modeDeJeu, boolean resultat, int nombredessais, String description, boolean devmod, int valeursaisie) {


    }
    /**
     * Permet de lancer le jeu et d'en selectionner un mode!.
     */
    public void jouer() {
        logger.info("Vous avez  : {} d'essais et {} cases à trouver", getNombreDessais(), getNombreDeChiffre());
        // switch case pour les modes simple, Defenseur et duel.
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

    private void jouerRecherchePlusMoinsChallenger(){  // 99% enlever les virgules entre les +,- dans le resultat.

        logger.info("Vous êtes en mode : Challenger vous devez tentez de deviner un code que l'ordinateur va generer !");
        int[] random = Utilitaire.creationDuRandom(getDevMod());
        int compteur = 0;
        int[] reponse = new int[2];
        boolean trouve = false;
        int nbEssais = getNombreDessais();
        int lenght = getNombreDeChiffre();
        char[] tabPlusOuMoins = new char[lenght];

        do {

            logger.info("\nEssai n° {} /  {}   :", (compteur + 1), nbEssais);
            int[] saisieClavier = SaisieClavier();
            trouve = ComparerCodeSecretAvecPlusOuMoins(saisieClavier, random,tabPlusOuMoins );

            logger.info("Proposition : {} -> Réponse : {}.", saisieClavier, tabPlusOuMoins);
            compteur++;

        } while (!trouve && compteur <= nbEssais);
        if (trouve) {
            logger.info("Bravo !!! Tu gagne en {} essais ", compteur);
        }
        if (!trouve && compteur == nbEssais) {
            logger.info("Tu as PERDU !!! tu as atteint tes {} essais ", compteur);
        }

    }

    private void jouerRecherchePlusMoinsDefenseur(){// Virgule dans resultat

        int lenght = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int compteurOrdi = 0;
        int[] reponse = new int[lenght];
        boolean trouve = false;
        String vainqueur = null;
        char[] tabPlusOuMoins = new char[lenght];
        logger.info("Saisissez le Code secret de {} chiffres, que l'ordinateur vas tenter de deviner.", lenght);
        int[] codeSecretSaisieParUtilisateur = SaisieClavier();
        logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);

        do {

            logger.info("\nEssai n° {} /  {} de l'{} :", Constante.IA, (compteurOrdi + 1), getNombreDessais());
            int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
            trouve = ComparerCodeSecretAvecPlusOuMoins(propositionDeLordinateur, codeSecretSaisieParUtilisateur,tabPlusOuMoins );
            logger.info("Proposition de l'{} : {} -> Reponse : {} .", Constante.IA, propositionDeLordinateur, tabPlusOuMoins);
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

    private void jouerRecherchePlusMoinsDuel(){  // gros probleme sur la comparaison de mon resultat, celui de la machine fonctionne

        int lenght = getNombreDeChiffre();
        int nbEssais = getNombreDessais();
        int[] codeGenereParLordi = Utilitaire.creationDuRandom(getDevMod()); // Virgule dans resultat
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
        char[] tabPlusOuMoins = new char[lenght];

        do {

            if (premierJouerEstUtilisateur) {
                logger.info("\nEssai n° {} /  {}   :", (compteur + 1), getNombreDessais());
                logger.info("Combinaison secrete de {} {}", Constante.IA, codeGenereParLordi);
                logger.info("Saisissez votre combinaison");
                codeTapeParUtilisateur = SaisieClavier();

                trouve = ComparerCodeSecretAvecPlusOuMoins(codeTapeParUtilisateur, codeGenereParLordi, tabPlusOuMoins );
                logger.info("Proposition : {} -> Réponse : {}.", codeTapeParUtilisateur, tabPlusOuMoins);
                compteur++;
                if (trouve) {
                    vainqueur = "Le joueur";

                }
                premierJouerEstUtilisateur = false;
            } else {
                logger.info("\nEssai n° {} /  {} de l'{} :", Constante.IA, (compteurOrdi + 1), getNombreDessais());
                logger.info("Le code secret que l'ordinateur doit tenter de deviner est {}", codeSecretSaisieParUtilisateur);
                int[] propositionDeLordinateur = Utilitaire.creationDuRandom(getDevMod());
                trouve = ComparerCodeSecretAvecPlusOuMoins(propositionDeLordinateur, codeSecretSaisieParUtilisateur,tabPlusOuMoins );
                logger.info("Proposition {} : {} -> Reponse : {}.", Constante.IA, propositionDeLordinateur,tabPlusOuMoins);
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


    public boolean ComparerCodeSecretAvecPlusOuMoins (int[] saisieClavier, int[] codeSecret,char[] tabPlusMoins) {
        int compteur = 0;
        for (int i = 0; i < saisieClavier.length; i++) {

            if (saisieClavier[i] < codeSecret[i]) {
                tabPlusMoins[i] = '+';
            } else if (saisieClavier[i] > codeSecret[i]) {
                tabPlusMoins[i] = '-';
            } else {
                tabPlusMoins[i] = '=';
                compteur++;
            }
        }

        if (compteur==saisieClavier.length){
            return true;
        }else

        return false;
    }
}
