package fr.oc.projet.enums;

/**
 * Creation de l'enum pour selectionner son mode de jeu.
 */
public enum EnumModeDeJeux {
    CHALLENGER(1, "Challenger"),
    DEFENDER(2, "Defender"),
    DUEL(3, "Duel");


    private int numero;
    private String nom;

    public int getNumero() {
        return numero;
    }

    public String getNom() {

        return nom;
    }

    EnumModeDeJeux(int code, String nom) {
        this.numero = code;
        this.nom = nom;
    }

    /**
     * Retourne l'enum en fonction du numero
     *
     * @param code
     * @return
     */
    public static EnumModeDeJeux getMode(int code) {
        for (EnumModeDeJeux modeDeJeux : EnumModeDeJeux.values()) {
            if (code == modeDeJeux.numero) {
                return modeDeJeux;
            }
        }
       return null;
    }

    public static TypeDeJeux getNom (int saisie) {
        for (TypeDeJeux g : TypeDeJeux.values()) {
            if (saisie == g.getCode()) {
                return g;
            }
        }
        return null;
    }

    /**
     * Retourne l'enum en fonction du numero
     *
     * @param code
     * @return
     */
    public static EnumModeDeJeux getMode2(int code) {
        EnumModeDeJeux[] tableauDesModesDeJeux = EnumModeDeJeux.values();
        for (int i = 0; i < tableauDesModesDeJeux.length; i++) {
            EnumModeDeJeux modeDeJeu = tableauDesModesDeJeux[i];
            if (code == modeDeJeu.numero) {
                return modeDeJeu;
            }
        }
        return null;
    }




}
