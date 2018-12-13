package fr.oc.projet.games;

import fr.oc.projet.enums.GameModeEnum;
import fr.oc.projet3.launcher.LoadProperties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Game {
    private String user;
    private GameModeEnum modeDeJeu;
    private int resultat;
    private int nombreDessais;
    private String description;

    public void setDevMod(boolean devMod) {
        this.devMod = devMod;
    }

    private boolean devMod;
    private int nombreDeChiffre;
    private int valeurSaisie;
    private Scanner sc;
    private static final char OK = 'o';
    private static final char KO = 'x';

    public Game() {
    }

    private static final Logger logger = LogManager.getLogger(Game.class);

    /**
     * Permet de lancer le jeu.
     */
    public void play() {
        logger.info("Game Launched");

    }

    /**
     * Constructeur vide qui permet d'appeler MasterMind sans lui donner de param.
     */

//    public Game() { //constructeur vide pour appeler sans rentrer toutes les infos
//        r = new Random();
//        sc = new Scanner(System.in);
//    }


    /**
     * Constructeur qui permet de construire le jeu hérité de game
     *
     * @param user          nom d'utilisateur
     * @param modeDeJeu     1/2/3
     * @param resultat      boolean gagné ou perdu
     * @param nombreDessais pour limiter le nombres d'essai il faut les compter
     * @param description   description du mode de jeu.
     * @param devMod        entrer dans le mode dev (combinaison connue).
     */
    public Game(String user, GameModeEnum modeDeJeu, int resultat, int nombreDessais, String description, boolean devMod, int nombreDeChiffre) {
        this.description = description;
        this.modeDeJeu = modeDeJeu;
        this.nombreDessais = nombreDessais;
        this.user = user;
        this.resultat = resultat;
        this.devMod = devMod;
        sc = new Scanner(System.in);

    }

    /**
     * Getter User
     *
     * @return utilisateur
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter user
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter de l'enum des types de jeu disponibles
     *
     * @return
     */
    public GameModeEnum getModeDeJeu() {
        return modeDeJeu;
    }

    /**
     * Setter de l'enum des types de jeu disponibles
     *
     * @return mode de jeu
     */
    public void setModeDeJeu(GameModeEnum modeDeJeu) {
        this.modeDeJeu = modeDeJeu;
    }

    /**
     * Getter resultat bool
     *
     * @return resultat
     */
    public int isResultat() {
        return resultat;
    }

    /**
     * Setter resultat bool
     *
     * @return resultat
     */
    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    /**
     * Getter Nbr d'essai
     *
     * @return nombreDessais
     */
    public int getNombreDessais() {
        nombreDessais = 4;
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
     * Getter description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter description
     *
     * @return description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Scanner Sc
     *
     * @return getteur de scanner
     */

    public Scanner getSc() {

        return sc;
    }

    /**
     * Scanner Sc
     *
     * @return setteur de scanner
     */
    public void setSc() {
        this.sc = sc;
    }

    /**
     * Valeur saisie par l'utilisateur
     *
     * @return setteur de valeur saisie
     */
    public int getValeurSaisie() {
        return valeurSaisie;
    }

    /**
     * Valeur saisie par l'utilisateur
     *
     * @return setteur de valeur saisie
     */
    public void setValeurSaisie(int valeurSaisie) {
        this.valeurSaisie = valeurSaisie;
    }

    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return getteur de nombreDeChiffre
     */
    public int getNombreDeChiffre() {

        this.nombreDeChiffre = Integer.parseInt(LoadProperties.NB_CASE_VALUE);
        return nombreDeChiffre;
    }

    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return setteur de nombreDeChiffre
     */
    public int setNombreDeChiffre(int nombreChiffre) {
        this.nombreDeChiffre = Integer.parseInt(LoadProperties.NB_CASE_VALUE);
        return nombreChiffre;
    }


    /**
     * Ok
     *
     * @return un symbole de ok
     */
    public static char getOK() {
        return OK;
    }

    /**
     * KO
     *
     * @return un symbole de KO
     */
    public static char getKO() {
        return KO;
    }
}
