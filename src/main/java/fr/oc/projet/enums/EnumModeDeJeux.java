package fr.oc.projet.enums;

/**
 * Creation de l'enum pour selectionner son mode de jeu.
 */
public enum EnumModeDeJeux {
    CHALLENGER(1),
    DEFENDER(2),
    DUEL(3);


    private int code;

    public int getCode() {
        return code;
    }

    EnumModeDeJeux(int code) {
        this.code = code;
    }

    /**
     * Creation de l'enum en fonction de la saisie
     *
     * @param saisie
     * @return
     */
    public static EnumModeDeJeux getMode(int saisie) {
        for (EnumModeDeJeux g : EnumModeDeJeux.values()) {
            if (saisie == g.code) {
                return g;
            }
        }
        return null;
    }
}
