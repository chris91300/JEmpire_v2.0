package models.batiments;

import models.Village;

public class Ferme {
    private int nourrituresProduitsChaqueJour = 5;
    private int quantiteBoisNecessaire = 2;
    private int quantiteFerme = 0;
    private int quantiteDeVillageoisNecessaireALaConstruction = 2;
    private int villageoisAuTravail = 0;

    public int recupereNourritureProduite() {
        return quantiteFerme * nourrituresProduitsChaqueJour;
    }

    public void construire(Village village) {
        int quantiteDeVillageoisDispo = village.getVillageoisNonActive();
        if (quantiteDeVillageoisDispo < quantiteDeVillageoisNecessaireALaConstruction) {
            System.out.println("Pas assez de villageois disponible pour construire une ferme");
        } else {

            int quantiteTotalBois = village.getBois();
            if (quantiteTotalBois < quantiteBoisNecessaire) {
                System.out.println("Pas assez de bois pour construire la ferme");
            } else {
                quantiteFerme++;
                village.retireBois(quantiteBoisNecessaire);
                System.out.println("Vous avez construit une maison");
                villageoisAuTravail += quantiteDeVillageoisNecessaireALaConstruction;
                village.deplaceVillageoisNonActifVersActif(quantiteDeVillageoisNecessaireALaConstruction);
            }
        }
    }

    public int recupereVillageoisQuiConstruisentUneFerme() {
        return villageoisAuTravail;
    }

}
