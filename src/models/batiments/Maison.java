package models.batiments;

import models.Village;

public class Maison {
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    private int capacite = 4;
    private int quantiteDeMaison = 1;
    private int quantiteBoisNecessaire = 2;


    public void construire(Village village) {
        int villageoisNonActif = village.getVillageoisNonActive();
        if(villageoisNonActif < quantiteDeVillageoisNecessaireALaConstruction){
            System.out.println("Vous n'avez pas assez de villageois de disponible");
        }else{
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
   
}
