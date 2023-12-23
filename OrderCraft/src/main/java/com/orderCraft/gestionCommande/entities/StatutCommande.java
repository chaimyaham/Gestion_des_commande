package com.orderCraft.gestionCommande.entities;

public enum StatutCommande {
    EN_COURS("En_cours"),
    TERMINEE("Terminee"),
    ANNULEE("Annulee");

    private final String libelle;

    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

}
