package fr.oc.projet3.launcher;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.enums.TypeDeJeux;
import fr.oc.projet.games.Jeu;
import fr.oc.projet.games.mastermind.Mastermind;
import fr.oc.projet.games.recherchePlusMoins.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
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
                        logger.error("Aucun mode detecté dans les proprietes.");
                }
            }else {
                logger.error("Pas de parametres dans les proprietes.");
            }
            }

        if (devOrProd != null) {

            Integer choix = selectGameType();
            Jeu jeu = getJeu(choix);
            logger.info("Vous entrez dans le jeu {}", TypeDeJeux.getMode(choix).getNom());

            // Selection du mode de jeu
            EnumModeDeJeux modeDeJeux = EnumModeDeJeux.getMode(selectGameMode());
            logger.info("Vous avez choisi le mode {}", modeDeJeux.getNom());
            jeu.setModeDeJeu(modeDeJeux);


            jeu.setDevMod(Constante.MODE_DEV.equals(devOrProd));
            jeu.setNombreDessais(Integer.parseInt(ChargementDesProprietes.NB_RETRY_VALUE));
            jeu.setNombreDeChiffre(Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE));
            jeu.jouer();

        } else {
            logger.error("Le jeu ne se lancera pas aucun args et rien dans les properties.");
        }
    }

    /**
     * Permet de choisir le type de jeu
     *
     * @param choix Integer 1ou 2
     * @return jeux
     */
    private static Jeu getJeu(Integer choix) {
        Jeu jeu = null;
        switch (choix) {
            case 1: {
                jeu = new Mastermind();
                break;
            }
            case 2: {
                jeu = new RecherchePlusMoins();
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
        return jeu;
    }

    /**
     * Choisir le mode de jeu
     *
     * @return le mode selectionné
     */
    private static Integer selectGameMode() {
        logger.info("Choisissez votre mode de jeux : ");
        for (EnumModeDeJeux modeDeJeux : EnumModeDeJeux.values()) {  // EnumModeDeJeux.values liste des enums de gamemode. La boucle for sert à parcourir les elements de ma liste enumere.
            logger.info("Pour {} tapez {}", modeDeJeux.getNom(), modeDeJeux.getNumero());
        }
        int mode = sc.nextInt();
        if (mode > EnumModeDeJeux.values().length || mode < 0) {
            logger.info("Vous avez choisi une valeur hors interval");
            selectGameMode();
        }
        return mode;
    }



    /**
     * méthode pour choisir le type de jeu.
     *
     * @return Scanner
     */
    private static Integer selectGameType() {
        logger.info("Bienvenue, il est temps de choisir votre type de jeu !");
        for (TypeDeJeux typeDeJeux : TypeDeJeux.values()) {
            logger.info("Tapez {} pour pour lancer {}", typeDeJeux.getCode(),typeDeJeux.getNom());
        }
        return sc.nextInt();
    }
}
