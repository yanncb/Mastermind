package fr.oc.projet3.launcher;

import fr.oc.projet.enums.GameModeEnum;
import fr.oc.projet.games.Game;
import fr.oc.projet.games.Mastermind;
import fr.oc.projet.games.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class);
    static Game game = new Game();


    public static void main(String[] args) {
        String devOrProd = null;
        if (args.length > 0) {
            switch (args[0]) {
                case Constants.MOD_DEV:
                    devOrProd = Constants.MOD_DEV;
                    logger.info("Mode Dev");
                    break;
                case Constants.MOD_PROD:
                    devOrProd = Constants.MOD_PROD;
                    logger.info("Mode PROD");
                    break;
                default:
                    logger.error("Le mode {} n'existe pas.", args[0]);
            }
        } else {
            logger.info("Pas de parametres, verification dans le fichier de proprietes.");
            // mettre un switch au lieu du if
            if (Constants.MOD_DEV.equals(LoadProperties.MOD_DEV_VALUE)) {

                devOrProd = Constants.MOD_DEV;
                logger.info("Mode Dev");


            } else if (LoadProperties.MOD_DEV_VALUE.equals("PROD")) {

                devOrProd = Constants.MOD_PROD;
                logger.info("Mode PROD");

            } else {
                logger.info("Aucun mode detecté dans les properties.");

            }
        }
        // executer le jeu ici :
        if (devOrProd != null) {


            Integer choice = selectGameType();
            Game game = getGame(choice);
            logger.info("Vous entrez dans le jeux {}" ,game.getClass().getSimpleName());
            Integer mode = selectGameMode();
            logger.info("Vous avez choisi le mode {}", GameModeEnum.getMode(mode));
            game.setModeDeJeu(GameModeEnum.getMode(mode));
            game.setDevMod(Constants.MOD_DEV.equals(devOrProd));
            game.play();
        } else {
            logger.error("Le jeu ne se lancera pas aucun args et rien dans les properties.");
        }
    }

    /**
     * Choisir le mode de jeu
     *
     * @return le mode selectionné
     */
    private static Integer selectGameMode() { //méthode pour recuperer le mode de jeu avec If
        logger.info("Choisissez votre mode de jeux : ");
        for(GameModeEnum gameModeEnum : GameModeEnum.values()){  // GameModeEnum.values liste des enums de gamemode. La boucle for sert à parcourir les elements de ma liste enumere.
            logger.info("Pour {} tapez {}" ,gameModeEnum.getCode(),gameModeEnum.name());
        }

        int mode = sc.nextInt();
        if (mode > GameModeEnum.values().length || mode < 0) { // le gamemodeenum.values permet de recuperer le tableau et le .lenght permet de comparer la longueur. ça permet egalement de pouvoir modifier les données par la suite (nombres de chiffres du mastermind).
            logger.info("Vous avez choisi une valeur hors interval");
            System.exit(-2);


        }
        return mode;
    }

    /**
     * Permet de choisir le type de jeu
     *
     * @param choice Integer 1ou 2
     * @return game
     */
    private static Game getGame(Integer choice) { // méthode pour choisir le type de jeu avec un Switch
        Game game = null;
        switch (choice) {
            case 1: {
                game = new Mastermind();
                Mastermind mastermind = new Mastermind();

                break;
            }
            case 2: {
                game = new RecherchePlusMoins();
                RecherchePlusMoins recherchePlusMoins = new RecherchePlusMoins();

                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
        return game;
    }

    /**
     * méthode pour choisir le type de jeu.
     *
     * @return Scanner
     */
    private static Integer selectGameType() {
        logger.info("Bienvenue, il est temps de choisir votre type de jeu !");
        logger.info("Tapez 1 pour : pour lancer une version du mastermind avec des indications moins précises que le premier mode");
        logger.info("Tapez 2 pour : rechercher une combinaison avec en retour des indications +/- pour trouver le résultat ");

        return sc.nextInt();
    }


}
