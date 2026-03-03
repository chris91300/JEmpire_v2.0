package models.batiments;

import models.Village;

abstract class Batiment {
    protected String type;
    protected int quantiteDeBoisNecessaireALaConstruction;
    protected int quantiteDePierreNecessaireALaConstruction;
    protected int quantiteDeVillageoisNecessaireALaConstruction;
    protected int quantiteDeVillageoisAuTravail = 0;
    protected int totalBatimentConstruit = 0;

    protected Batiment(String type, int total, int quantiteDeVillageoisNecessaire, int quantiteDeBoisNecessaire, int quantiteDePierreNecessire){
        this.type = type;
        this.totalBatimentConstruit = total;
        this.quantiteDeVillageoisNecessaireALaConstruction = quantiteDeVillageoisNecessaire;
        this.quantiteDeBoisNecessaireALaConstruction = quantiteDeBoisNecessaire;
        this.quantiteDePierreNecessaireALaConstruction = quantiteDePierreNecessire;
    }

    public void construire(Village village){
        if(quantiteDeBoisNecessaireALaConstruction > 0 ){
            int quantiteTotalBois = village.getBois();
            if (quantiteTotalBois < quantiteDeBoisNecessaireALaConstruction) {
                throw new Error("Pas assez de bois pour construire votre " + type);
                
            } 
        }
        

        if(quantiteDePierreNecessaireALaConstruction > 0){
        int quantiteTotalPierre = village.getPierre();
            if (quantiteTotalPierre < quantiteDePierreNecessaireALaConstruction) {
                throw new Error("Pas assez de bois pour construire votre " + type);
                
            }
        }

         

        // ceci doit être fait en fin de journée
        totalBatimentConstruit++;
        //quantiteDeVillageoisAuTravail += quantiteDeVillageoisNecessaireALaConstruction;
        village.retireBois(quantiteDeBoisNecessaireALaConstruction);
        village.retirePierre(quantiteDePierreNecessaireALaConstruction);
        System.out.println("Vous avez construit votre "+type);
        // voir gestion de la capacité
        // gerer ça dans la class maison dans un override
        //village.setCapacite(capacite * quantiteDeMaison);
    
    }


    public void lesVillageoisSeReposent(){
        quantiteDeVillageoisAuTravail = 0;
    }
}
