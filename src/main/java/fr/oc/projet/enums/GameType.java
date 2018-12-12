package fr.oc.projet.enums;

public enum GameType {
    MASTER_MIND(1),
    MASTER_MIND_MINUS_PLUS(2);

    private int type; // type est la valeur entre parenthese apres MASTER_MIND(!!!)

    GameType(int type) { // declaré ici
        this.type = type; // this.type = type permet de ne pas modifié la valeur du type de base
    }
}
