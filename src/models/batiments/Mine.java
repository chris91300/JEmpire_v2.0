package models.batiments;

import models.Village;

public class Mine extends Batiment {
    private int capaciteMaxDansUneMine = 2;
    private int nombreDeVillageoisDansLesMines = 0;
    private int quantiteMaxDePierreTrouve = 4;
    private int quantiteMaxDeFerTrouve = 3;

    public Mine(){
        super("mine", 0, 0, 0, 0);
    }


   public void ajouteAuTotalDeMines(int quantiteTrouvee){
        totalBatimentConstruit += quantiteTrouvee;
   }

    public void ajouteVillageoisDansLesMines(int quantite, Village village){
        if(totalBatimentConstruit <= 0){
            throw new Error("Vous n'avez pas encore decouvert de mine.");
        }

        int capaciteTotal = totalBatimentConstruit * capaciteMaxDansUneMine - nombreDeVillageoisDansLesMines;
        if(quantite > capaciteTotal){
            throw new Error("Il n'y a que "+capaciteTotal+" places.");
           
        }

        nombreDeVillageoisDansLesMines += quantite;
    }

    // on peut obtenir soit des pierres soit du fer
    public int[] obtenirRessourcesDuJour(){
        int nombreVillageoisQuiATrouvePierre = randomRessources(0,nombreDeVillageoisDansLesMines);
        int pierreTrouve = randomRessources(nombreVillageoisQuiATrouvePierre, nombreVillageoisQuiATrouvePierre * quantiteMaxDePierreTrouve);
        int nombreVillageoisQuiATrouveFer = nombreDeVillageoisDansLesMines - nombreVillageoisQuiATrouvePierre;
        int ferTrouve = randomRessources(nombreVillageoisQuiATrouveFer, nombreVillageoisQuiATrouveFer * quantiteMaxDeFerTrouve);
        int[] ressourcesTrouvees = {pierreTrouve, ferTrouve};

        if(nombreDeVillageoisDansLesMines > 0){
            System.out.printf(
                "dans les mines vous avez trouvé : %d pierres, %d fer.\n",
                pierreTrouve,
                ferTrouve
            );
        }
        nombreDeVillageoisDansLesMines = 0;
        return ressourcesTrouvees;
    }

    private int randomRessources(int min, int max ){      
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
