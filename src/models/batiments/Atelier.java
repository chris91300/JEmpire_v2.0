package models.batiments;

import models.Village;

public class Atelier {
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    private int quantiteDeBoisNecessaireALaConstruction = 8;
    private int quantiteDePierreNecessaireALaConstruction = 10;
    private int quantiteTotalAtelier = 0;
    private int capaciteTotalDeVillageoisEnFormationParAtelier = 1;
    private int villageoisEnFormation = 0; 
    private int villageoisAuTravail = 0;
    // voir apres pour les outils et amelioration batiments

    public void construire(Village village){
        int villageoisNonActif = village.getVillageoisNonActive();
        if(villageoisNonActif < quantiteDeVillageoisNecessaireALaConstruction){
            System.out.println("Vous n'avez pas assez de villageois de disponible");
        }else{
            int quantiteTotalDePierreEnStock = village.getPierre();
            int quantiteTotalDeBoisEnStock = village.getBois();
            boolean quantiteInsuffisante = (quantiteTotalDePierreEnStock < quantiteDePierreNecessaireALaConstruction) || (quantiteTotalDeBoisEnStock < quantiteDeBoisNecessaireALaConstruction);
        
            if(quantiteInsuffisante){
                if(quantiteTotalDePierreEnStock < quantiteDePierreNecessaireALaConstruction){
                    System.out.println("Vous n'avez pas assez de pierre.");
                }
                if(quantiteTotalDeBoisEnStock < quantiteDeBoisNecessaireALaConstruction){
                    System.out.println("Vous n'avez pas assez de bois.");
                }
            }else{
                village.retirePierre(quantiteDePierreNecessaireALaConstruction);
                village.retireBois(quantiteDeBoisNecessaireALaConstruction);               
                village.deplaceVillageoisNonActifVersActif(quantiteDeVillageoisNecessaireALaConstruction);
                 quantiteTotalAtelier++;
                 villageoisAuTravail += quantiteDeVillageoisNecessaireALaConstruction;
                 village.deplaceVillageoisNonActifVersActif(quantiteDeVillageoisNecessaireALaConstruction);
            }
        }
        
    }

    public void villageoisEnFormation(int villageoisVoulantEtreForme, Village village){
        int placesDisponibles = quantiteTotalAtelier * capaciteTotalDeVillageoisEnFormationParAtelier - villageoisEnFormation;
        
        
        if(villageoisVoulantEtreForme > placesDisponibles){
            System.out.println("Il n'y a pas assez de place.");
        }else{
            villageoisEnFormation += villageoisVoulantEtreForme;
            village.deplaceVillageoisNonActifVersActif(villageoisVoulantEtreForme);
        }
    }

    public int recupereVillageoisEnFormation(){
         int villageoisRecuperes = villageoisEnFormation;
        villageoisEnFormation = 0;
        return villageoisRecuperes;
    }

    public int recupereVillageoisQuiOntConstruitAtelier(){
        int villageoisRecuperes = villageoisAuTravail;
        villageoisAuTravail = 0;
        return villageoisRecuperes;
    }
}
