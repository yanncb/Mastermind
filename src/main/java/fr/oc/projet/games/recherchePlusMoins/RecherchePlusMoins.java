package fr.oc.projet.games.recherchePlusMoins;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.games.Jeu;
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

    private void jouerRecherchePlusMoinsChallenger(){

        logger.info("Vous êtes en mode : Challenger vous devez tentez de deviner un code que l'ordinateur va generer !");
        int[] random = Utilitaire.creationDuRandom(getDevMod());
        int compteur = 0;
        int[] reponse = new int[2];
        boolean trouve = false;
        int nbEssais = getNombreDessais();
        int lenght = getNombreDeChiffre();
        char[] tabPlusOuMoins = new char[lenght];
        int tabSansVirgule = 0;
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

    private void jouerRecherchePlusMoinsDefenseur(){

    }

    private void jouerRecherchePlusMoinsDuel(){

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
