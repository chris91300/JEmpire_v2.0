package models.batiments;

import models.Village;

public class Foret {
    private int totalVillageoisPouvantAllerEnForet = 4;
    private int foretsTrouvees = 1;
    private int villageoisEnForet = 0;
    private int quantiteMaxDeNourriturePouvantEtreTrouve = 4;
    private int quantiteMaxDeBoisPouvantEtreTrouve = 6;
    private int quantiteMaxDeVillageoisePouvantEtreTrouve = 2;
    private int quantiteMaxDeMinesPouvantEtreTrouve = 1;

    public void villageoisVoulantPartirEnForet(int villageoisVoulantPartir, Village village){
        int capaciteMax = foretsTrouvees * totalVillageoisPouvantAllerEnForet - villageoisEnForet;
        if(villageoisVoulantPartir > capaciteMax){
            System.out.println("Plus de place disponible.");
        }else{
            villageoisEnForet += villageoisVoulantPartir;
            village.deplaceVillageoisNonActifVersActif(villageoisVoulantPartir);
        }
    }

   

    private int randomRessources(int min, int max ){      
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public int[] recuperereRessourcesTrouvees(){
        // nourriture, bois, villageois;
        // attention au random(0, 0) !!! a gérer
        int nombreDeVillageoisQuiOntTrouveNourriture = randomRessources(0,villageoisEnForet);
        int nourritureTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveNourriture, nombreDeVillageoisQuiOntTrouveNourriture * quantiteMaxDeNourriturePouvantEtreTrouve);
       
        int nombreDeVillageoisQuiOntTrouveBois = randomRessources(0,villageoisEnForet - nombreDeVillageoisQuiOntTrouveNourriture);
        int boisTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveBois, nombreDeVillageoisQuiOntTrouveBois * quantiteMaxDeBoisPouvantEtreTrouve);

        int nombreDeVillageoisQuiOntTrouveVillageois = randomRessources(0,villageoisEnForet - nombreDeVillageoisQuiOntTrouveNourriture - nombreDeVillageoisQuiOntTrouveNourriture);
        int villageoisTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveVillageois, nombreDeVillageoisQuiOntTrouveVillageois * quantiteMaxDeVillageoisePouvantEtreTrouve);

        int nombreDeVillageoisQuiOntTrouveMines = randomRessources(0,villageoisEnForet - nombreDeVillageoisQuiOntTrouveNourriture - nombreDeVillageoisQuiOntTrouveNourriture - nombreDeVillageoisQuiOntTrouveVillageois);
        int minesTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveMines, nombreDeVillageoisQuiOntTrouveMines * quantiteMaxDeMinesPouvantEtreTrouve);

        int[] ressources = {nourritureTrouve, boisTrouve, villageoisTrouve, minesTrouve};
        return ressources;
    }


    public int recupereVillageoisPartientEnForet(){
        return villageoisEnForet;
    }
}
