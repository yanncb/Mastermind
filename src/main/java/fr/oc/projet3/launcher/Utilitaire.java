package fr.oc.projet3.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
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

    /**
     * creation d'un tableau contenant des numeros aleatoire de taille (NB_CASE_VALUE)
     * @param existsRandoms Si un code generé aleatoirement existe deja il en sera regeneré un nouveau, et le nouveau sera stocké dans la liste.
     * @return un random de taille (NB_CASE_VALUE)
     */
    public static int[] creationDuRandom(List<Integer> existsRandoms) {

        int nbCaseValue = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
        int[] resultatDuRandom = new int[nbCaseValue];
        Random random = new Random();
        boolean randomExist = false;
        do {
            for (int i = 0; i < nbCaseValue; i++) {
                resultatDuRandom[i] = random.nextInt(9);
            }
            Integer value = Integer.valueOf(resultatDuRandom.toString());
            if(existsRandoms.contains(value)){
                randomExist = true;
            } else {
                existsRandoms.add(value);
            }
        }while (randomExist);
        return resultatDuRandom;
    }

}
