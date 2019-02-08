package fr.oc.projet.enums;

public enum TypeDeJeux {
    MASTER_MIND(1, "Mastermind"),
    MASTER_RECHERCHE_PLUS_MOINS(2, "MasterMind recherche plus moins");

    private String nom;
    private int code;

    TypeDeJeux(int code, String nom) {

        this.code = code;
        this.nom = nom;
    }

    /**
     * Creation de l'enum en fonction de la saisie
     *
     * @param saisie
     * @return
     */
    public static TypeDeJeux getMode(int saisie) {
        for (TypeDeJeux g : TypeDeJeux.values()) {
            if (saisie == g.code) {
                return g;
            }
        }
        return null;
    }
//
//    public static TypeDeJeux getNom (int saisie) {
//        for (TypeDeJeux g : TypeDeJeux.values()) {
//            if (saisie == g.code) {
//                return g;
//            }
//        }
//        return null;
//    }


    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }
}