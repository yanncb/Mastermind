package fr.oc.projet.enums;

public enum EnumModeDeJeux { // permet de faire des choix uniques
    CHALLENGER(1),
    DEFENDER(2),
    DUEL(3);


    private int code; // code est la valeur entre parenthese

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
