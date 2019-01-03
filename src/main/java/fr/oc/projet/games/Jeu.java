package fr.oc.projet.games;

import fr.oc.projet.enums.EnumModeDeJeux;
import fr.oc.projet3.launcher.ChargementDesProprietes;
import fr.oc.projet3.launcher.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Jeu {
    private String user;
    private EnumModeDeJeux modeDeJeu;
    private int resultat;
    private int nombreDessais;
    private String description;


    /**
     * devMod est la valeur de devOrProd du main.
     */
    private boolean devMod;
    private int nombreDeChiffre;
    private int valeurSaisie;
    private static final char OK = 'o';
    private static final char KO = 'x';

    public Jeu() {
    }

    private static final Logger logger = LogManager.getLogger(Jeu.class);

    /**
     * Permet de lancer le jeu.
     */
    public void jouer() {
        logger.info("Jeu Lancé");

    }

    /**
     * Constructeur vide qui permet d'appeler MasterMind sans lui donner de param.
     */

//    public Jeu() { //constructeur vide pour appeler sans rentrer toutes les infos
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
    public Jeu(String user, EnumModeDeJeux modeDeJeu, int resultat, int nombreDessais, String description, boolean devMod, int nombreDeChiffre) {
        this.description = description;
        this.modeDeJeu = modeDeJeu;
        this.nombreDessais = nombreDessais;
        this.user = user;
        this.resultat = resultat;
        this.devMod = devMod;

    }
    public void retrymod() {  //------------------------------------------------------------------A VERIFIER
        logger.info("Souhaitez vous refaire une partie ? Si oui Tapez 1, Si non Tapez 2");
        int retry = Main.sc.nextInt();
            switch (retry){
                case 1 :
                   // relancer le jeu
                    break;
                case 2 :
                    logger.info("Aurevoir !");
                    break;
                default:
                    logger.info("Saisie invalide le jeu vas se terminer. Vous pouvez relancer le jeu à tout moment ! Au revoir !");
            }
    }

    /**
     * Methode qui comme son nom l'indique permet de recuperer les saisie claviers en les mettant dans un tableau de int.
     *
     * @return saisieplayer
     */
    public int[] recursiveSaisieClavier() {
        // mettre methode en recursive verif si bon nombre de chiffre sinon rappel saisieclavier -------
        int[] saisieJoueur = new int[getNombreDeChiffre()]; // creation d'un tableau de taille nbcase
        String nombreSaisi = Main.sc.nextLine();
        for (int i = 0; i < getNombreDeChiffre(); i++) {
            saisieJoueur[i] = Integer.parseInt(String.valueOf(nombreSaisi.charAt(i)));// transformer la saisie en tableau.
        }
        return saisieJoueur;
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

    public void setDevMod(boolean devMod) {
        this.devMod = devMod;
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
        this.nombreDessais = Integer.parseInt(ChargementDesProprietes.NB_RETRY_VALUE);
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

        this.nombreDeChiffre = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
        return nombreDeChiffre;
    }

    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return setteur de nombreDeChiffre
     */
    public int setNombreDeChiffre(int nombreChiffre) {
        this.nombreDeChiffre = Integer.parseInt(ChargementDesProprietes.NB_CASE_VALUE);
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
