package fr.oc.projet.games.mastermind;

import fr.oc.projet.enums.GameModeEnum;
import fr.oc.projet.games.Game;
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
        switch (getModeDeJeu().getCode()) {
            case 1: {
                jouerMastermindSimple();
                break;
            }
            case 2: {
                jouerMastermindDefenseur();
                break;
            }
            case 3: {
                jouerMastermindChallenger();
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }

        }



    }


    private int [] saisieClavier() {
        int nbCase = Integer.parseInt(LoadProperties.NB_CASE_VALUE);
        int[] saisiePlayer = new int[nbCase];
        Scanner sc = new Scanner(System.in);
        int nombreSaisi = sc.nextInt();

        String saisie = String.valueOf(nombreSaisi);

        for (int i = 0; i < nbCase; i++) {
            saisiePlayer[i] = Integer.parseInt(String.valueOf(saisie.charAt(i)));// transformer la saisie en tableau.
        }
        sc.close();
        return saisiePlayer;
    }

    private void commencerJeux(){
        logger.info("Nombres d'essais : {}", LoadProperties.NB_RETRY_VALUE);
        logger.info("Nombres d'objets : {}", LoadProperties.NB_ITEM_VALUE);
        logger.info("Nombres de cases à trouver : {}", LoadProperties.NB_CASE_VALUE);
    }

    private void jouerMastermindSimple(){

        commencerJeux();

        int nbCaseValue = Integer.parseInt(LoadProperties.NB_CASE_VALUE);
        int[] resultatDuRandom = new int[nbCaseValue];
        Random random = new Random();


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

//        int[] saisiePlayer = new int[nbCaseValue]; // initialisation d'un tableau pour recuperer les saisie utilisateur.
        boolean victoire = false;
        do {
            logger.info("\nEssai n° {} /  {} +  :",(counter + 1),LoadProperties.NB_RETRY_VALUE);
            int[] saisiePlayer = saisieClavier();

            // On écrit la proposition du joueur
            logger.info(Arrays.toString(saisiePlayer));

            victoire = true; // on met à vrai pour l'instant
            for (int i = 0; i < nbCaseValue; i++) {
                boolean bonChiffre = saisiePlayer[i] == resultatDuRandom[i];
                if (bonChiffre) {
                    logger.info(getOK());
                } else {
                    logger.info(getKO());
                }
                victoire = victoire && bonChiffre; // victoire sera vrai UNIQUEMENT si bonChiffre vaut vrai a CHAQUE tour de boucle
            }

            counter++;

            if (counter == nbCaseValue)
                logger.info("Vos chances sont épuisés {} essais, c'est perdu pour vous...",nbCaseValue );

        } while (!victoire && counter < Integer.parseInt(LoadProperties.NB_RETRY_VALUE));
        logger.info("En seulement {} coups" ,counter );

    }

    private void jouerMastermindChallenger() {
        commencerJeux();
    }

    private void jouerMastermindDefenseur() {
        commencerJeux();

    }
}




