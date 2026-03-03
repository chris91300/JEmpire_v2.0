package models.batiments;

import models.Village;
//import models.unites.Villageois;

public class Caserne extends Batiment {
    //private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    //private int quantiteDePierreNecessaire = 4;
    private int capaciteMaxEnFormationDansUneCaserne = 2;
    private int totalCaserne = 0;
    private int nombreDeVillageoisEnFormationSoldat = 0;
    private int nombreDeVillageoisEnFormationEclaireur = 0;
    

    public Caserne(){
        super("caserne", 0, 2, 0, 4);
    }


    public void ajouteVillageoisEnFormation(int quantite, String formation, Village village){
        if(totalCaserne == 0){
            throw new Error("Vous n'avez pas encore de caserne.");
        }

        int villageoisEnFormation = nombreDeVillageoisEnFormationEclaireur + nombreDeVillageoisEnFormationSoldat;
        int capaciteTotal = totalCaserne * capaciteMaxEnFormationDansUneCaserne -villageoisEnFormation;
        
        if(quantite > capaciteTotal){
            throw new Error("Il n'y a que "+capaciteTotal+" places.");
        }

         if(formation.equals("soldat")){
            nombreDeVillageoisEnFormationSoldat += quantite;
        }else{
            nombreDeVillageoisEnFormationEclaireur += quantite;
        }
        
    }

    public int[] recupereVillageoisEnFormation(){
        int soldatRecuperes = nombreDeVillageoisEnFormationSoldat;
        nombreDeVillageoisEnFormationSoldat = 0;
        int eclaireurRecuperes = nombreDeVillageoisEnFormationEclaireur;
        nombreDeVillageoisEnFormationEclaireur = 0;
        int[] villageoisFormes = {soldatRecuperes, eclaireurRecuperes};
        return villageoisFormes;
    }


    public int getQuantiteDeVillageoisNecessaireALaConstruction(){
        return quantiteDeVillageoisNecessaireALaConstruction;
    }
}
