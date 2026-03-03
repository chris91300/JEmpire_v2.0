package models.batiments;

//import models.Village;

public class Ferme extends Batiment {
    private int nourrituresProduitsChaqueJour = 5;
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    
    public Ferme(){
        super("ferme", 0, 2, 2, 0);
    }

    public int recupereNourritureProduite() {
        return totalBatimentConstruit * nourrituresProduitsChaqueJour;
    }
   

    public int getQuantiteDeVillageoisNecessaireALaConstruction(){
        return quantiteDeVillageoisNecessaireALaConstruction;
    }

}
