package models.batiments;

import models.Village;

public class MurDeDefense extends Batiment {
    private int quantiteDeVillageoisNecessaireALaConstruction = 5;
    private int quantiteNecessaireDePierrePourLaConstruction = 20;

    public MurDeDefense(){
        super("mur de defense", 0, 5, 0, 20);
    }


    public void construire(Village village){
        if(totalBatimentConstruit > 0){
            throw new Error("Vous avez déjà construit un mur");
        }

        if(quantiteDeVillageoisAuTravail == 2){
            throw new Error("Des villageois sont déjà assignés à cette tâche.");
        }

        int quantiteTotalDePierre = village.getPierre();
        if(quantiteTotalDePierre < quantiteNecessaireDePierrePourLaConstruction){
            throw new Error("Vos ressources sont insuffisante");
        }

        village.retirePierre(quantiteNecessaireDePierrePourLaConstruction);
        totalBatimentConstruit++;
        
    }

    public int getQuantiteDeVillageoisNecessaireALaConstruction(){
        return quantiteDeVillageoisNecessaireALaConstruction;
    }

}
