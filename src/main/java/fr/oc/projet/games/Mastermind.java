package fr.oc.projet.games;

import fr.oc.projet.enums.GameModeEnum;
import fr.oc.projet3.launcher.LoadProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Mastermind extends Game {

    private static final Logger logger = LogManager.getLogger(Mastermind.class);
    // public final GameType type = GameType.MASTER_MIND; // fonction permettant d'appeler mastermind

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
    public Mastermind(String user, GameModeEnum modeDeJeu, int resultat, int nombredessais, String description, boolean devmod, int nombreChiffre) {
        super(user, modeDeJeu, resultat, nombredessais, description, devmod, nombreChiffre);
    }

    /**
     * Permet de lancer le mode de jeu.
     */
    public void play() {

        // switch case pour les modes chall, duel etc.


        int nbCaseValue = Integer.parseInt(LoadProperties.NB_CASE_VALUE);
        int[] resultatDuRandom = new int[nbCaseValue];
        Random random = new Random();

        Scanner sc = new Scanner(System.in);


        logger.info("Nombres d'essais : {}", LoadProperties.NB_RETRY_VALUE);
        logger.info("Nombres d'objets : {}", LoadProperties.NB_ITEM_VALUE);
        logger.info("Nombres de cases à trouver : {}", LoadProperties.NB_CASE_VALUE);
        /**
         * Je genere mon code à découvrir dans le Mastermind avec random.
         */
        for (int i = 0; i < nbCaseValue; i++) {
            resultatDuRandom[i] = random.nextInt(nbCaseValue + 1);
        }
        logger.info("Le code à découvrir est : {}", Arrays.toString(resultatDuRandom));
        logger.info(getModeDeJeu());

        int counter = 0;

        logger.info("Mastermind, trouve la combinaison des {} chiffres entre 0 et 9 .", LoadProperties.NB_CASE_VALUE);
        logger.info("{} siginife que c'est le bon chiffre à la bonne place et {} signifie que c'est le mauvais chiffre.", getOK(), getKO());
        logger.info("Attention, vous avez droit à {} essais.", LoadProperties.NB_RETRY_VALUE);
        logger.info("----------------------------");

        int[] saisiePlayer = new int[nbCaseValue]; // initialisation d'un tableau pour recuperer les saisie utilisateur.
        boolean victoire = false;
        do {
            System.out.println("\nEssai n° " + (counter + 1) + "/" + LoadProperties.NB_RETRY_VALUE + " :");
            int nombreSaisi = sc.nextInt();

            String saisie = String.valueOf(nombreSaisi);

            for (int i = 0; i < nbCaseValue; i++) {
                saisiePlayer[i] = Integer.parseInt(String.valueOf(saisie.charAt(i)));// transformer la saisie en tableau.
            }

            // On écrit la proposition du joueur
            System.out.println(Arrays.toString(saisiePlayer));

            victoire = true; // on met à vrai pour l'instant
            for (int i = 0; i < nbCaseValue; i++) {
                boolean bonChiffre = saisiePlayer[i] == resultatDuRandom[i];
                if (bonChiffre) {
                    System.out.println(getOK());
                } else {
                    System.out.println(getKO());
                }
                victoire = victoire && bonChiffre; // victoire sera vrai UNIQUEMENT si bonChiffre vaut vrai a CHAQUE tour de boucle
            }

            counter++;

            if (counter == nbCaseValue)
                System.out.println("Vos chances sont épuisés " + nbCaseValue + " essais, c'est perdu pour vous...");

        } while (!victoire && counter < Integer.parseInt(LoadProperties.NB_RETRY_VALUE));
        System.out.println("En seulement " + counter + " coups");
        sc.close();
    }
}




