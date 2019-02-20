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

    /**
     * @param code de l'enum
     * @param nom  de l'enum
     */
    EnumModeDeJeux(int code, String nom) {
        this.numero = code;
        this.nom = nom;
    }

    /**
     * choix de l'enum en fonction du numero
     *
     * @param code numero de l'enum.
     * @return L'enum choisie
     */
    public static EnumModeDeJeux getMode(int code) {
        for (EnumModeDeJeux modeDeJeux : EnumModeDeJeux.values()) {
            if (code == modeDeJeux.numero) {
                return modeDeJeux;
            }
        }
        return null;
    }

    /**
     * getteur nom
     *
     * @param saisie
     * @return
     */
    public static TypeDeJeux getNom(int saisie) {
        for (TypeDeJeux g : TypeDeJeux.values()) {
            if (saisie == g.getCode()) {
                return g;
            }
        }
        return null;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {

        return nom;
    }


}
