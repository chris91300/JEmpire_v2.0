package models.batiments;

import models.Village;

public class Atelier extends Batiment {
    private int quantiteTotalAtelier = 0;
    private int capaciteTotalDeVillageoisEnFormationParAtelier = 1;
    private int villageoisEnFormation = 0; 

    public Atelier(){
        super("atelier", 0, 2, 8, 10);
    }
    // voir apres pour les outils et amelioration batiments


    public void villageoisEnFormation(int villageoisVoulantEtreForme, Village village){
        if(quantiteTotalAtelier == 0){
            throw new Error("Vous n'avez pas encore d'atelier.");
        }
        int placesDisponibles = quantiteTotalAtelier * capaciteTotalDeVillageoisEnFormationParAtelier - villageoisEnFormation;
        
        if(villageoisVoulantEtreForme > placesDisponibles){
            throw new Error("Il n'y a pas assez de place.");
        }

        villageoisEnFormation += villageoisVoulantEtreForme;
    }

    public int recupereVillageoisEnFormation(){
         int villageoisRecuperes = villageoisEnFormation;
        villageoisEnFormation = 0;
        return villageoisRecuperes;
    }


    public int getQuantiteDeVillageoisNecessaireALaConstruction(){
        return quantiteDeVillageoisNecessaireALaConstruction;
    }


}
