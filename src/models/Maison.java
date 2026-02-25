package models;

public class Maison {
    private int capacite = 4;
    private int quantite = 1;
    private int quantiteBoisNecessaire = 2;

    public void setPopulation(int capacite) {
        this.capacite = capacite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void construire(Village village) {
        quantite++;

    }
    // village.ajouteCapacite(4);
    // village.ajouteVillageois(1);
}
