package models.batiments;

import models.Village;

public class Caserne {
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    private int quantiteDePierreNecessaire = 4;
    private int capaciteMaxEnFormationDansUneCaserne = 2;
    private int totalCaserne = 0;
    private int nombreDeVillageoisEnFormationSoldat = 0;
    private int nombreDeVillageoisEnFormationEclaireur = 0;


    public void construire(Village village){
        int villageoisNonActif = village.getVillageoisNonActive();
        if(villageoisNonActif < quantiteDeVillageoisNecessaireALaConstruction){
            System.out.println("Vous n'avez pas assez de villageois de disponible");
        }else{
            int quantiteTotalDePierre = village.getPierre();
            if(quantiteTotalDePierre < quantiteDePierreNecessaire){
                System.out.println("Vos ressources sont insuffisante");
            }else{
                village.retirePierre(quantiteDePierreNecessaire);
                totalCaserne++;
                village.deplaceVillageoisNonActifVersActif(quantiteDeVillageoisNecessaireALaConstruction);
            }
        }
    }

    public void ajouteVillageoisEnFormation(int quantite, String formation, Village village){
        int villageoisEnFormation = nombreDeVillageoisEnFormationEclaireur + nombreDeVillageoisEnFormationSoldat;
        int capaciteTotal = totalCaserne * capaciteMaxEnFormationDansUneCaserne -villageoisEnFormation;
        if(quantite > capaciteTotal){
            System.out.printf("Il n'y a que %d places.\n",  capaciteTotal);
        }else{
            int population = village.getVillageoisNonActive();
            if(quantite > population){
                System.out.println("Il n'y a pas assez de villageois disponibles.");
            }else{
                if(formation.equals("soldat")){
                    nombreDeVillageoisEnFormationSoldat += quantite;
                }else{
                    nombreDeVillageoisEnFormationEclaireur += quantite;
                }
                village.deplaceVillageoisNonActifVersActif(quantite);
                
            }
        }
    }

    public int[] recupereVillageoisEnFormation(){
        int[] villageoisFormes = {nombreDeVillageoisEnFormationSoldat, nombreDeVillageoisEnFormationEclaireur};
        return villageoisFormes;
    }
}
