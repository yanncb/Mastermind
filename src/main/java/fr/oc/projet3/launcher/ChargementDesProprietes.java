package fr.oc.projet3.launcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class ChargementDesProprietes {
    /**
     * Nom de la valeur de la propriete dans le fichier properties
     */
    public static String NB_RETRY_NAME = "nb.retry";
    public static String NB_RETRY_VALUE;
    public static String NB_CASE_NAME = "nb.case";
    public static String NB_CASE_VALUE;
    public static String NB_ITEM_NAME = "nb.item";
    public static String NB_ITEM_VALUE;
    public static String MOD_DEV_NAME = "application.mode";
    public static String MOD_DEV_VALUE;


    static {
        try (InputStream input = ChargementDesProprietes.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            prop.load(input);
            MOD_DEV_VALUE = prop.getProperty(MOD_DEV_NAME);
            NB_RETRY_VALUE = prop.getProperty(NB_RETRY_NAME);
            NB_CASE_VALUE = prop.getProperty(NB_CASE_NAME);
            NB_ITEM_VALUE = prop.getProperty(NB_ITEM_NAME);

        } catch (final IOException ex) {
            ex.printStackTrace();// Indique l'exception sur le flux d'erreur standard
        }
    }
}
