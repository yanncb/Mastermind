package fr.oc.projet.enums;

public enum TypeDeJeux {
    MASTER_MIND(1),
    MASTER_RECHERCHE_PLUS_MOINS(2);

    private int type; // type est la valeur entre parenthese apres MASTER_MIND(!!!)

    TypeDeJeux(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    /**
     * Creation de l'enum en fonction de la saisie
     *
     * @param saisie
     * @return
     */
    public static TypeDeJeux getMode(int saisie) {
        for (TypeDeJeux g : TypeDeJeux.values()) {
            if (saisie == g.type) {
                return g;
            }
        }
        return null;
    }
}