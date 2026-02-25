package models.batiments;

import models.Village;

public class MurDeDefense {
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    private int quantiteNecessaireDePierrePourLaConstruction = 20;
    private boolean murDeDefenseEstConstruit = false;
    private int villageoisAuTravail = 0;


    public void construire(Village village){
        if(!murDeDefenseEstConstruit){
            if(villageoisAuTravail == 2){
                System.out.println("Des villageois sont déjà assignés à cette tâche.");
            }else{
                int villageoisNonActif = village.getVillageoisNonActive();
                if(villageoisNonActif < quantiteDeVillageoisNecessaireALaConstruction){
                    System.out.println("Vous n'avez pas assez de villageois de disponible");
                }else{
                    int quantiteTotalDePierre = village.getPierre();
                    if(quantiteTotalDePierre < quantiteNecessaireDePierrePourLaConstruction){
                        System.out.println("Vos ressources sont insuffisante");
                    }else{
                        village.retirePierre(quantiteNecessaireDePierrePourLaConstruction);
                        murDeDefenseEstConstruit = true;;
                    }
                }
            }
            
        }else{
            System.out.println("Vous avez déjà construit un mur");
        }
        
    }

    public int recupererVillageois(){
        return villageoisAuTravail;
    }

}
