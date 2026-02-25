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
        int personnesQuiOntRienTrouveesPourLeMoment = villageoisEnForet;
        int nourritureTrouve = 0;
        int boisTrouve = 0;
        int villageoisTrouve = 0;
        int minesTrouve = 0;

        if(personnesQuiOntRienTrouveesPourLeMoment > 0){
            int nombreDeVillageoisQuiOntTrouveNourriture = randomRessources(0, personnesQuiOntRienTrouveesPourLeMoment);
            nourritureTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveNourriture, nombreDeVillageoisQuiOntTrouveNourriture * quantiteMaxDeNourriturePouvantEtreTrouve);
            personnesQuiOntRienTrouveesPourLeMoment -= nombreDeVillageoisQuiOntTrouveNourriture;
           
            if(personnesQuiOntRienTrouveesPourLeMoment > 0){
                int nombreDeVillageoisQuiOntTrouveBois = randomRessources(0, personnesQuiOntRienTrouveesPourLeMoment);
                boisTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveBois, nombreDeVillageoisQuiOntTrouveBois * quantiteMaxDeBoisPouvantEtreTrouve);
                personnesQuiOntRienTrouveesPourLeMoment -=  nombreDeVillageoisQuiOntTrouveBois;

                if(personnesQuiOntRienTrouveesPourLeMoment > 0){
                     int nombreDeVillageoisQuiOntTrouveVillageois = randomRessources(0,personnesQuiOntRienTrouveesPourLeMoment);
                    villageoisTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveVillageois, nombreDeVillageoisQuiOntTrouveVillageois * quantiteMaxDeVillageoisePouvantEtreTrouve);
                   personnesQuiOntRienTrouveesPourLeMoment -=  nombreDeVillageoisQuiOntTrouveVillageois;

                    if(personnesQuiOntRienTrouveesPourLeMoment > 0){
                        int nombreDeVillageoisQuiOntTrouveMines = randomRessources(0, personnesQuiOntRienTrouveesPourLeMoment);
                        minesTrouve = randomRessources(nombreDeVillageoisQuiOntTrouveMines, nombreDeVillageoisQuiOntTrouveMines * quantiteMaxDeMinesPouvantEtreTrouve);
                    }
                    
                }
            }
        }
        
        int[] ressources = {nourritureTrouve, boisTrouve, villageoisTrouve, minesTrouve};
        return ressources;
    }


    public int recupereVillageoisPartientEnForet(){        
        int villageoisRecuperes = villageoisEnForet;
        villageoisEnForet = 0;        
        return villageoisRecuperes;
    }
}
