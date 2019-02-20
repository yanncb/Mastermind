package fr.oc.projet3.launcher;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet.enums.TypeDeJeux;
import fr.oc.projet.games.Jeu;
import fr.oc.projet.games.mastermind.Mastermind;
import fr.oc.projet.games.recherchePlusMoins.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static String devOrProd;

    public static void main(String[] args) {

        devOrProd = null;
        String modeDeLancement = null;
        if ( args.length > 0) {
            modeDeLancement = args[0];
        } else if (ChargementDesProprietes.MOD_DEV_VALUE != null) {
            logger.info("Pas de parametres, verification dans le fichier de proprietes.");
            modeDeLancement = ChargementDesProprietes.MOD_DEV_VALUE;
        } else {
            logger.error("Pas de parametres dans les proprietes.");
        }

        if(modeDeLancement != null) {
            switch (modeDeLancement) {
                case Constante.MODE_DEV:
                    devOrProd = Constante.MODE_DEV;
                    logger.info("Mode Dev");
                    break;
                case Constante.MODE_PROD:
                    devOrProd = Constante.MODE_PROD;
                    logger.info("Mode PROD");
                    break;
                default:
                    logger.error("Le mode {} n'existe pas.", modeDeLancement);
            }
        }

        if (devOrProd != null) {

            Jeu jeu = lancementJeu(devOrProd);

            retrymod(jeu);

        } else {
            logger.error("Le jeu ne se lancera pas aucun args et rien dans les properties.");
        }
    }

    public static Jeu lancementJeu(String devOrProd) {
        Integer choix = selectionnerJeu();
        Jeu jeu = getJeu(choix);
        logger.info("Vous entrez dans le jeu {}", TypeDeJeux.getMode(choix).getNom());

        EnumModeDeJeux modeDeJeux = EnumModeDeJeux.getMode(selectionnerModeDeJeu());
        logger.info("Vous avez choisi le mode {}", modeDeJeux.getNom());
        jeu.setModeDeJeu(modeDeJeux);


        jeu.setDevMod(Constante.MODE_DEV.equals(devOrProd));
        jeu.setNombreDessais(Integer.parseInt(ChargementDesProprietes.NB_RETRY_VALUE));
        jeu.setNombreDeChiffre(Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE));
        jeu.jouer();

        return jeu;

    }
    /**
     * Permet de creer le jeu
     *
     * @param choix Integer 1ou 2
     * @return jeux
     */
    public static Jeu getJeu(Integer choix) {
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
            case 3: {
                logger.info("A bientot !");
                System.exit(-1);
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
            }
        }
        return jeu;
    }

    /**
     * Choisir le mode de jeu en fonction de l'enum
     *
     * @return le mode selectionné
     */
    public static Integer selectionnerModeDeJeu() {
        int mode = 0;
        try {
            logger.info("Choisissez votre mode de jeux : ");
            for (EnumModeDeJeux modeDeJeux : EnumModeDeJeux.values()) {
                logger.info("Tapez {} pour {}", modeDeJeux.getNumero(), modeDeJeux.getNom());
            }
            mode = sc.nextInt();
        } catch (InputMismatchException e) {
            logger.error("Caractere numerique uniquement");
            sc.nextLine();
            selectionnerModeDeJeu();
        }

        if (mode > EnumModeDeJeux.values().length || mode < 0) {
            logger.info("Vous avez choisi une valeur hors interval");
            selectionnerModeDeJeu();
        }
        return mode;
    }

    /**
     * méthode pour choisir le type de jeu.
     *
     * @return le mode selectionné
     */
    public static Integer selectionnerJeu() {
        int mode = 0;
        try {
            logger.info("Bienvenue, il est temps de choisir votre type de jeu !");
            for (TypeDeJeux typeDeJeux : TypeDeJeux.values()) {
                logger.info("Tapez {} pour lancer {}", typeDeJeux.getCode(), typeDeJeux.getNom());
            }
            mode = sc.nextInt();
        } catch (InputMismatchException e) {
            logger.error("Caractere numerique uniquement");
            sc.nextLine();
            selectionnerJeu();
        }
        if (mode > EnumModeDeJeux.values().length || mode < 0) {
            logger.info("Vous avez choisi une valeur hors interval");
            selectionnerJeu();
        }
        return mode;
    }

    /**
     * Methode qui permet de relancer le jeu ou d'en choisir un autre.
     */
    public static void retrymod(Jeu jeu) {
        int rejouer = 0;

        try {
            logger.info("Souhaites tu rejouer au même jeu ? Oui (1) / Choisir un autre Jeu (2) / Quitter (3)");
            rejouer = sc.nextInt();
        } catch (InputMismatchException e) {
            logger.error("Caractere numerique uniquement");
            sc.nextLine();
            retrymod(jeu);
        }

        switch (rejouer) {

            case 1:
                jeu.jouer();
                retrymod(jeu);
                break;

            case 2:
                lancementJeu(devOrProd);
                retrymod(jeu);
                break;

            case 3:
                System.exit(0);
                break;

            default:
                break;
        }
    }
}
