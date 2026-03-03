package models.batiments;

import models.Village;

public class Maison extends Batiment{
    private int capacite = 4;

    public Maison(){
        super("maison", 1, 2, 2, 0);
    }


    @Override
    public void construire(Village village) {        
        super.construire(village);
        village.setCapacite(capacite * totalBatimentConstruit);
    }
   
    public int getQuantiteDeVillageoisNecessaireALaConstruction(){
        return quantiteDeVillageoisNecessaireALaConstruction;
    }
}
