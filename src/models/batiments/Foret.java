package models.batiments;

import models.Village;

public class Foret extends Batiment {
    private int totalVillageoisPouvantAllerEnForet = 4;
    private int villageoisEnForet = 0;
    private int quantiteMaxDeNourriturePouvantEtreTrouve = 4;
    private int quantiteMaxDeBoisPouvantEtreTrouve = 6;
    private int quantiteMaxDeVillageoisePouvantEtreTrouve = 2;
    private int quantiteMaxDeMinesPouvantEtreTrouve = 1;

    public Foret(){
        super("foret", 1, 0, 0, 0);
    }
   

    public void villageoisVoulantPartirEnForet(int villageoisVoulantPartir, Village village){
        int capaciteMax = totalBatimentConstruit * totalVillageoisPouvantAllerEnForet - villageoisEnForet;
        if(villageoisVoulantPartir > capaciteMax){
            throw new Error("Plus de place disponible.");
            
        }else{
            villageoisEnForet += villageoisVoulantPartir;
        }
    }
 

    private int randomRessources(int min, int max ){      
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public int[] recuperereRessourcesTrouvees(){
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
        
        
        if(villageoisEnForet > 0){
             System.out.printf(
                "Vous avez trouvé dans la forêt : %d personnes, %d nourritures, %d bois, %d mines.\n",
                villageoisTrouve,
                nourritureTrouve,
                boisTrouve,
                minesTrouve
            );
        }
       
        villageoisEnForet = 0;
        int[] ressources = {nourritureTrouve, boisTrouve, villageoisTrouve, minesTrouve};
        return ressources;
    }

    @Override
    public void lesVillageoisSeReposent(){
        villageoisEnForet = 0;
    }
}
