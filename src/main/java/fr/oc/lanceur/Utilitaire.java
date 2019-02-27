package fr.oc.lanceur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * La classe utilitaire contient l'algorithme de creation du random et l'algo qui repertorie les differents code deja generé pour ne pas
 * les reutiliser.
 */
public class Utilitaire {
    private static final Logger logger = LogManager.getLogger(Utilitaire.class);

    /**
     * Creer un nombre random du nombre cases defini ex :  Nombre de case 4  resultat  : [1,4,5,6]
     *
     * @param devOrProd savoir si le mode est en developpeur ou en production
     * @return un tableau de chiffres aléatoire
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
            logger.info("Le code genere par l'ordinateur aleatoirement est : {} ", Arrays.toString(resultatDuRandom));
        }

        return resultatDuRandom;
    }

    /**
     * creation d'un tableau contenant des numeros aleatoire de taille (NB_CASE_VALUE)
     *
     * @param existsRandoms Si un code generé aleatoirement existe deja il en sera regeneré un nouveau, et le nouveau sera stocké dans la liste.
     * @return un random de taille (NB_CASE_VALUE)
     */
    public static int[] creationDuRandom(List<Integer> existsRandoms) {

        int nbCase = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
        int[] resultatDeLAleatoire = new int[nbCase];
        Random random = new Random();
        boolean aleatoireExiste = false;
        do {
            for (int i = 0; i < nbCase; i++) {
                resultatDeLAleatoire[i] = random.nextInt(9);
            }
            Integer valeur = Integer.valueOf(resultatDeLAleatoire.toString());
            if (existsRandoms.contains(valeur)) {
                aleatoireExiste = true;
            } else {
                existsRandoms.add(valeur);
            }
        } while (aleatoireExiste);
        return resultatDeLAleatoire;
    }


}
