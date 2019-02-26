package fr.oc.projet.enums;

/**
 * Type énumeré listant les modes de jeux.
 */
public enum ModeDeJeux {
    CHALLENGER(1, "Challenger"),
    DEFENDER(2, "Defender"),
    DUEL(3, "Duel");

    private int numero;
    private String nom;

    /**
     * @param code de l'enum
     * @param nom  de l'enum
     */
    ModeDeJeux(int code, String nom) {
        this.numero = code;
        this.nom = nom;
    }

    /**
     * choix de l'enum en fonction du numero
     *
     * @param code numero de l'enum.
     * @return L'enum choisie
     */
    public static ModeDeJeux getMode(int code) {
        for (ModeDeJeux modeDeJeux : ModeDeJeux.values()) {
            if (code == modeDeJeux.numero) {
                return modeDeJeux;
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
