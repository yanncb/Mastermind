package fr.oc.projet.games;

import fr.oc.projet.enums.EnumModeDeJeux;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;


public abstract class Jeu {
    public static Scanner scan = new Scanner(System.in);
    private EnumModeDeJeux modeDeJeu;
    private int nombreDessais;
    private boolean devMod;
    private int nombreDeChiffre;

    public Jeu() {
    }

    private static final Logger logger = LogManager.getLogger(Jeu.class);

    /**
     * Permet de lancer le jeu.
     */
    public void jouer() {
    }



    /**
     * Methode qui comme son nom l'indique permet de recuperer les saisie claviers en les mettant dans un tableau de int.
     *
     * @return saisiejoueur
     */
    public int[] SaisieClavier() {
       boolean saisieOk = false;
       int length = getNombreDeChiffre();
       int[] saisieJoueur = new int[length];
       do {
           String nombreSaisi = scan.nextLine();
           if (nombreSaisi.length()!=length) {
               logger.info("La longueur du code saisie ne correspond pas a la valeur attendue.");
               logger.info("Reessayez une saisie sur {}.", length);
               saisieOk = false;
           } else {
             //   rajouter saisie joueur dans un tableau pour le mettre ensuite dans la saisie player.
               for (int i = 0; i < length; i++) {
                   saisieJoueur[i] = Integer.parseInt(String.valueOf(nombreSaisi.charAt(i)));// transformer la saisie en tableau.
               }

               saisieOk = true;
           }
       }while (!saisieOk);

       return saisieJoueur;
    }


    /**
     * Getter de l'enum des types de jeu disponibles
     *
     * @return
     */
    public EnumModeDeJeux getModeDeJeu() {
        return modeDeJeu;
    }

    /**
     * Setter de l'enum des types de jeu disponibles
     *
     * @return mode de jeu
     */
    public void setModeDeJeu(EnumModeDeJeux modeDeJeu) {
        this.modeDeJeu = modeDeJeu;
    }

    /**
     * Setter du boolean si devMod ou pas.
     *
     * @return mode de jeu
     */
    public void setDevMod(boolean devMod) {
        this.devMod = devMod;
    }

    /**
     * Getter Nbr d'essai
     *
     * @return nombreDessais
     */
    public int getNombreDessais() {
        return nombreDessais;
    }

    /**
     * Setter Nbr d'essai
     *
     * @return nombreDessais
     */
    public void setNombreDessais(int nombreDessais) {
        this.nombreDessais = nombreDessais;
    }

    /**
     * Getter du Developpeur mode.
     *
     * @return mode boolean
     */
    public boolean getDevMod() {
        return devMod;
    }


    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return getteur de nombreDeChiffre
     */
    public int getNombreDeChiffre() {
        return nombreDeChiffre;
    }

    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return setteur de nombreDeChiffre
     */
    public void setNombreDeChiffre(int nombreChiffre) {
        this.nombreDeChiffre = nombreChiffre;
    }
}
