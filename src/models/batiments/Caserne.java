package models.batiments;

import models.Village;

public class Caserne {
    private int quantiteDePierreNecessaire = 4;
    private int capaciteMaxEnFormationDansUneCaserne = 2;
    private int totalCaserne = 0;
    private int nombreDeVillageoisEnFormation = 0;


    public void construire(Village village){
        int quantiteTotalDePierre = village.getPierre();
        if(quantiteTotalDePierre < quantiteDePierreNecessaire){
            System.out.println("Vos ressources sont insuffisante");
        }else{
            village.retirePierre(quantiteDePierreNecessaire);
            totalCaserne++;
        }
    }

    public void ajouteVillageoisDansLesMines(int quantite, Village village){
        int capaciteTotal = totalCaserne * capaciteMaxEnFormationDansUneCaserne;
        if(quantite > capaciteTotal){
            System.out.printf("Il n'y a que %d places.\n",  capaciteTotal);
        }else{
            int population = village.getPopulationNonActive();
            if(quantite > population){
                System.out.println("Il n'y a pas assez de villageois disponibles.");
            }else{
                village.deplaceVillageois(quantite);
                nombreDeVillageoisEnFormation += quantite;
            }
        }
    }
}
