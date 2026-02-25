package models;

public class Maison {
    private int capacite = 4;
    private int quantiteDeMaison = 1;
    private int quantiteBoisNecessaire = 2;

    /*public void setPopulation(int capacite) {
        this.capacite = capacite;
    }*/

    /*public void setQuantite(int quantite) {
        this.quantiteDeMaison = quantite;
    }*/

    public void construire(Village village) {
        int quantiteTotalBois = village.getBois();
        if (quantiteTotalBois < quantiteBoisNecessaire) {
            System.out.println("Pas assez de bois pour construire la maison");
        } else {
            quantiteDeMaison++;
            village.retireBois(quantiteBoisNecessaire);
            System.out.println("Vous avez construit une maison");
            village.setCapacite(capacite * quantiteDeMaison);
         }

    }
   
}
