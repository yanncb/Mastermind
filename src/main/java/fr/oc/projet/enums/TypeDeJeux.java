package fr.oc.projet.enums;

/**
 * Type énumeré listant les jeux.
 */
public enum TypeDeJeux {
    MASTER_MIND(1, "Mastermind"),
    MASTER_RECHERCHE_PLUS_MOINS(2, "Recherche plus moins"),
    QUITTE(3, "Quitter le programme");

    private String nom;
    private int code;

    /**
     * constructeur enum
     *
     * @param code de l'enum
     * @param nom  de l'enum
     */
    TypeDeJeux(int code, String nom) {

        this.code = code;
        this.nom = nom;
    }

    /**
     * Selection du type de jeu .
     *
     * @param saisie choix utilisateur
     * @return rien
     */
    public static TypeDeJeux getMode(int saisie) {
        for (TypeDeJeux typeDeJeux : TypeDeJeux.values()) {
            if (saisie == typeDeJeux.code) {
                return typeDeJeux;
            }
        }
        return null;
    }

    /**
     * accesseur getteur
     *
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * accesseur getteur
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }
}