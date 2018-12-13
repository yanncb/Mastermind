package fr.oc.projet.games.recherchePlusMoins;

import fr.oc.projet.enums.GameModeEnum;
import fr.oc.projet.games.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecherchePlusMoins extends Game {

    private static final Logger logger = LogManager.getLogger(RecherchePlusMoins.class);

    /**
     * Constructeur vide qui permet d'appeler RecherchePlusMoins sans lui donner de param.
     */
    public RecherchePlusMoins() {
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
    public RecherchePlusMoins(String user, GameModeEnum modeDeJeu, boolean resultat, int nombredessais, String description, boolean devmod, int valeursaisie) {


    }

    /**
     * Permet de lancer le mode de jeu.
     */
    public void play() {
        logger.info(" Recherche Plus Moins !");
    }


}
