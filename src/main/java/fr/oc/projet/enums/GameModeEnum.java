package fr.oc.projet.enums;

public enum GameModeEnum { // permet de faire des choix uniques
    CHALLENGER(1),
    DEFENDER(2),
    DUEL(3);


    private int code; // code est la valeur entre parenthese

    public int getCode() {
        return code;
    }

    GameModeEnum(int code) {
        this.code = code;
    }

    /**
     * Creation de l'enum en fonction de la saisie
     *
     * @param saisie
     * @return
     */
    public static GameModeEnum getMode(int saisie) {
        for (GameModeEnum g : GameModeEnum.values()) {
            if (saisie == g.code) {
                return g;
            }
        }
        return null;
    }
}
