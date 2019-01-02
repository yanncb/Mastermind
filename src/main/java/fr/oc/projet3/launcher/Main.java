package fr.oc.projet3.launcher;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.games.Jeux;
import fr.oc.projet.games.mastermind.Mastermind;
import fr.oc.projet.games.recherchePlusMoins.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String devOrProd =null;
        if (args.length > 0) {
            switch (args[0]) {
                case Constante.MODE_DEV:
                    devOrProd = Constante.MODE_DEV;
                    logger.info("Mode Dev");
                    break;
                case Constante.MODE_PROD:
                    devOrProd = Constante.MODE_PROD;
                    logger.info("Mode PROD");
                    break;
                default:
                    logger.error("Le mode {} n'existe pas.", args[0]);

            }
        } else {
            logger.info("Pas de parametres, verification dans le fichier de proprietes.");
            if(ChargementDesProprietes.MOD_DEV_VALUE!=null) {
                switch (ChargementDesProprietes.MOD_DEV_VALUE) {
                    case Constante.MODE_DEV:
                        devOrProd = Constante.MODE_DEV;
                        logger.info("mode DEV");
                        break;
                    case Constante.MODE_PROD:
                        devOrProd = Constante.MODE_PROD;
                        logger.info("Mode PROD");
                        break;
                    default:
                        logger.info("Aucun mode detecté dans les proprietes.");
                }
            }else {
                logger.error("Pas de parametres dans les proprietes.");
            }
            }
        // executer le jeu ici :
        if (devOrProd != null) {

            Integer choice = selectGameType();
            Jeux jeux = getGame(choice);
            logger.info("Vous entrez dans le jeux {}", jeux.getClass().getSimpleName());
            Integer mode = selectGameMode();
            logger.info("Vous avez choisi le mode {}", EnumModeDeJeux.getMode(mode));
            jeux.setModeDeJeu(EnumModeDeJeux.getMode(mode));
            jeux.setDevMod(Constante.MODE_DEV.equals(devOrProd));
            jeux.setNombreDessais(Integer.parseInt(ChargementDesProprietes.NB_RETRY_VALUE));
            jeux.setNombreDeChiffre(Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE));
            jeux.jouer();

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
        for (EnumModeDeJeux enumModeDeJeux : EnumModeDeJeux.values()) {  // EnumModeDeJeux.values liste des enums de gamemode. La boucle for sert à parcourir les elements de ma liste enumere.
            logger.info("Pour {} tapez {}", enumModeDeJeux.name(), enumModeDeJeux.getCode());
        }
        int mode = sc.nextInt();
        if (mode > EnumModeDeJeux.values().length || mode < 0) { // le gamemodeenum.values permet de recuperer le tableau et le .lenght permet de comparer la longueur. ça permet egalement de pouvoir modifier les données par la suite (nombres de chiffres du mastermind).
            logger.info("Vous avez choisi une valeur hors interval");
            System.exit(-2);
        }
        return mode;
    }
//    private static Mastermind launchMode(Integer mode) {
//        Mastermind mastermind = null;
//        switch (mode) {
//            case 1:{
//                mastermind = new Mastermind();
//                break;
//            }
//            case 2:{
//                mastermind = new MasterMindDefenseur();
//                MasterMindDefenseur masterMindDefenseur = new MasterMindDefenseur();
//                break;
//            }
//            case 3 :{
//                mastermind = new MastermindDuel();
//                MastermindDuel mastermindDuel = new MastermindDuel();
//                break;
//            }
//            default: {
//                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
//                System.exit(-1);
//            }
//
//        }
//        return mastermind;
//    }

    /**
     * Permet de choisir le type de jeu
     *
     * @param choice Integer 1ou 2
     * @return jeux
     */
    private static Jeux getGame(Integer choice) { // méthode pour choisir le type de jeu avec un Switch
        Jeux jeux = null;
        switch (choice) {
            case 1: {
                jeux = new Mastermind();
                break;
            }
            case 2: {
                jeux = new RecherchePlusMoins();
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
        return jeux;
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
