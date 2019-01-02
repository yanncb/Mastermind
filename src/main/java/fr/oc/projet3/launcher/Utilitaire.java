package fr.oc.projet3.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class Utilitaire {
    private static final Logger logger = LogManager.getLogger(Utilitaire.class);

    /**
     * Creer un nombre random du nombre cases defini ex :  Nombre de case 4  resultat  : [1,4,5,6]
     *
     * @return
     */
    public static int[] creationDuRandom(boolean devOrProd) {

        int nbCaseValue = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
        int[] resultatDuRandom = new int[nbCaseValue];
        Random random = new Random();


        /**
         * Je genere mon code à découvrir dans le Mastermind avec random.
         */
        for (int i = 0; i < nbCaseValue; i++) {
            resultatDuRandom[i] = random.nextInt(9);
        }

        if (devOrProd) {
            logger.info("Le code genere par l'ordinateur aleatoirement est : {}", Arrays.toString(resultatDuRandom));
        }

        return resultatDuRandom;
    }
    public static int[] creationDuRandomSansModeDev() {

        int nbCaseValue = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
        int[] resultatDuRandom = new int[nbCaseValue];
        Random random = new Random();

        /**
         * Je genere mon code à découvrir dans le Mastermind avec random.
         */
        for (int i = 0; i < nbCaseValue; i++) {
            resultatDuRandom[i] = random.nextInt(9);
        }
        return resultatDuRandom;
    }

}
