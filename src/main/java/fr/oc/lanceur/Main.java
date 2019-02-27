package fr.oc.lanceur;

import fr.oc.projet.enums.ModeDeJeux;
import fr.oc.projet.enums.TypeDeJeux;
import fr.oc.projet.jeux.Jeu;
import fr.oc.projet.jeux.mastermind.Mastermind;
import fr.oc.projet.jeux.recherchePlusMoins.RecherchePlusMoins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static String modeLancement;

    /**
     * Classe principale de lancement du programme.
     *
     * @param args paramètre de lancement du main.
     */
    public static void main(String[] args) {

        String paramLancement = null;
        if (args.length > 0) {
            paramLancement = args[0];
        } else if (ChargementDesProprietes.MOD_DEV_VALUE != null) {
            logger.info("Pas de paramètres, verification dans le fichier de propriétes.");
            paramLancement = ChargementDesProprietes.MOD_DEV_VALUE;
        } else {
            logger.error("Pas de paramètres dans les proprietes. Sortie du jeu");
            System.exit(0);
        }

        switch (paramLancement) {
            case Constante.MODE_DEV:
                modeLancement = Constante.MODE_DEV;
                logger.info("Mode Dev");
                break;
            case Constante.MODE_PROD:
                modeLancement = Constante.MODE_PROD;
                logger.info("Mode PROD");
                break;
            default:
                logger.error("Le mode {} n'existe pas.  Sortie du jeu", paramLancement);
                System.exit(0);
        }
        retryMod(lancementJeu(modeLancement));

    }

    /**
     * Méthode qui permet de choisir un jeu, le mode du jeu et de le lancer.
     *
     * @param modeLancement Mode de lancement du jeu : DEV ou PROD
     * @return jeu : retourne l'instance de jeu paramétré.
     */
    public static Jeu lancementJeu(String modeLancement) {

        Integer choix = selectionnerJeu();
        Jeu jeu = getJeu(choix);
        logger.info("Vous entrez dans le jeu {}", TypeDeJeux.getMode(choix).getNom());

        ModeDeJeux modeDeJeux = ModeDeJeux.getMode(selectionnerModeDeJeu());
        logger.info("Vous avez choisi le mode {}", modeDeJeux.getNom());
        jeu.setModeDeJeu(modeDeJeux);

        jeu.setDevMod(Constante.MODE_DEV.equals(modeLancement));
        jeu.setNombreDessais(Integer.parseInt(ChargementDesProprietes.NB_RETRY_VALUE));
        jeu.setNombreDeChiffre(Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE));
        jeu.jouer();

        return jeu;

    }

    /**
     * Permet de creer le jeu
     *
     * @param choix paramètre indiquant le code de jeu à lancer.
     * @return jeu
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
                break;
            }
            default: {
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");

                lancementJeu(modeLancement);

            }
        }
        return jeu;
    }

    /**
     * Choisir le mode de jeu.
     *
     * @return le mode selectionné
     */
    public static Integer selectionnerModeDeJeu() {
        int mode = 0;
        boolean saisieOk = false;
        do {
            try {
                logger.info("Choisissez votre mode de jeux : ");
                for (ModeDeJeux modeDeJeux : ModeDeJeux.values()) {
                    logger.info("Tapez {} pour {}", modeDeJeux.getNumero(), modeDeJeux.getNom());
                }
                mode = sc.nextInt();

                if (mode > ModeDeJeux.values().length || mode <= 0) {
                    logger.info("Vous avez choisi une valeur hors interval");
                    saisieOk = false;
                } else {
                    saisieOk = true;
                }
            } catch (InputMismatchException e) {
                logger.error("Caractere numerique uniquement");
                sc.nextLine();
                saisieOk = false;
            }
        } while (!saisieOk);

        return mode;
    }

    /**
     * méthode pour choisir le type de jeu.
     *
     * @return le mode selectionné
     */
    public static Integer selectionnerJeu() {
        int mode = 0;
        boolean saisieOK = false;
        do {
            try {
                logger.info("Bienvenue, il est temps de choisir votre type de jeu !");
                for (TypeDeJeux typeDeJeux : TypeDeJeux.values()) {
                    logger.info("Tapez {} pour lancer {}", typeDeJeux.getCode(), typeDeJeux.getNom());
                }
                mode = sc.nextInt();
                if (mode > ModeDeJeux.values().length || mode <= 0) {
                    logger.info("Vous avez choisi une valeur hors interval");
                    saisieOK = false;
                } else {
                    saisieOK = true;
                }
            } catch (InputMismatchException e) {
                logger.error("Caractere numerique uniquement");
                sc.nextLine();
                saisieOK = false;
            }
        }
        while (!saisieOK);

        return mode;
    }

    /**
     * Methode qui permet de relancer le jeu ou d'en choisir un autre.
     *
     * @param jeu à rejouer {@link Jeu}
     */
    public static void retryMod(Jeu jeu) {
        int choixUtilisateur = 0;

        try {
            logger.info("Souhaites tu rejouer au même jeu ? Oui (1) / Choisir un autre Jeu (2) / Quitter (3)");
            choixUtilisateur = sc.nextInt();
        } catch (InputMismatchException e) {
            logger.error("Caractère numerique uniquement");
            sc.nextLine();
            retryMod(jeu);
        }

        switch (choixUtilisateur) {

            case 1:
                jeu.jouer();
                retryMod(jeu);
                break;

            case 2:
                lancementJeu(modeLancement);
                retryMod(jeu);
                break;

            case 3:
                System.exit(0);
                break;

            default:
                retryMod(jeu);
                break;
        }
    }
}
