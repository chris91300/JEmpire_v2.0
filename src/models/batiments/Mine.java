package models.batiments;

import models.Village;

public class Mine {
    private int capaciteMaxDansUneMine = 2;
    private int totalMine = 0;
    private int nombreDeVillageoisDansLesMines = 0;
    private int quantiteMaxDePierreTrouve = 4;
    private int quantiteMaxDeFerTrouve = 3;


   public void ajouteAuTotalDeMines(int quantiteTrouvee){
        totalMine += quantiteTrouvee;
   }

    public void ajouteVillageoisDansLesMines(int quantite, Village village){
        int capaciteTotal = totalMine * capaciteMaxDansUneMine;
        if(quantite > capaciteTotal){
            System.out.printf("Il n'y a que %d places.\n",  capaciteTotal);
        }else{
            int population = village.getVillageoisNonActive();
            if(quantite > population){
                System.out.println("Il n'y a pas assez de villageois disponibles.");
            }else{
                village.deplaceVillageoisNonActifVersActif(quantite);
                nombreDeVillageoisDansLesMines += quantite;
            }
        }
    }

    // on peut obtenir soit des pierres soit du fer
    public int[] obtenirRessourcesDuJour(){
        int nombreVillageoisQuiATrouvePierre = randomRessources(0,nombreDeVillageoisDansLesMines);
        int pierreTrouve = randomRessources(nombreVillageoisQuiATrouvePierre, nombreVillageoisQuiATrouvePierre * quantiteMaxDePierreTrouve);
        int nombreVillageoisQuiATrouveFer = nombreDeVillageoisDansLesMines - nombreVillageoisQuiATrouvePierre;
        int ferTrouve = randomRessources(nombreVillageoisQuiATrouveFer, nombreVillageoisQuiATrouveFer * quantiteMaxDeFerTrouve);
        int[] ressourcesTrouvees = {pierreTrouve, ferTrouve};
        return ressourcesTrouvees;
    }

    private int randomRessources(int min, int max ){      
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public int recupereVillageoisPartiALaMine(){
        return nombreDeVillageoisDansLesMines;
    }
}
