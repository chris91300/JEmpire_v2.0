package models;

import java.util.Scanner;

import models.batiments.Caserne;
import models.batiments.Ferme;
import models.batiments.Foret;
import models.batiments.Maison;
import models.batiments.Mine;
import models.batiments.Atelier;
import models.batiments.MurDeDefense;
import models.ressources.Ressource;

public class Village {
    static Scanner scanner = new Scanner(System.in);
    private int capacite = 4;
    private int villageoisNonActive = 4;
    private int villageoisActive = 0;
    private int soldatsNonActive = 0;
    private int soldatsActive = 0;
    private int eclaireursNonActive = 0;
    private int eclaireursActive = 0;
    private int artisansNonActive = 0;
    private int artisansActive = 0;
    private int chefNonActive = 0;
    private int chefActive = 0;
    private Maison maison = new Maison();
    private Caserne caserne = new Caserne();;
    private Ferme ferme = new Ferme();
    private Mine mine = new Mine();
    private Foret foret = new Foret();
    private Atelier atelier = new Atelier();
    private MurDeDefense murDeDefense = new MurDeDefense();
    private Ressource ressource = new Ressource();
    private int jour = 1;
    private boolean isOver = false;
    private boolean journeeTerminee = false;

    public void start() {
        while (!isOver) {
            journeeTerminee = false;
            while (!journeeTerminee) {
                afficherJour();
                ressource.afficheRessources();
                afficherVillageoisNonActif();
                int choixDuMenu = afficherMenuGlobal();
                int action = proposeMenuEnFonctionDuPersonnage(choixDuMenu);
                faitAction(action);
                if (isOver) {
                    break;
                }
            }
            if (isOver) {
                break;
            }
            updateRessources();
            lesVillageoisOntFiniLeurtache();
            desGensVeulentrejoindreLeVillage();
            lesVillageoisMangent();
            YEnAvaitIlPourToutLeMonde();
            if (isOver) {
                break;
            }
            ajoutJour();
        }
    }

    private void afficherJour() {
        System.out.println("****************************************");
        System.out.printf("\t\tJOUR %d\t\t\n", jour);
        System.out.println("****************************************");

    }

    private void afficherVillageoisNonActif() {
        System.out.println("****************************************");
        System.out.println("\t\tDISPONIBLE\t\t");
        System.out.printf(
            "villageois: %d | soldat: %d | éclaireur: %d | artisan: %d | chef: %d \n",
            villageoisNonActive,
            soldatsNonActive,
            eclaireursNonActive,
            artisansNonActive,
            chefNonActive
        );
        System.out.println("****************************************");
    }

    private int afficherMenuGlobal(){
        int numberActionMax = 1;
        System.out.println("que fait on aujourd'hui?");
        System.out.println("assigner des villageois à une tache: 1");

        if(soldatsNonActive > 0){
            System.out.println("assigner des soldats à une tache: 2");
            numberActionMax++;
        }

        if(artisansNonActive > 0){
            System.out.println("assigner des artisans à une tache: 3");
            numberActionMax++;
        }

        if(eclaireursNonActive > 0){
            System.out.println("assigner des éclaireurs à une tache: 4");
            numberActionMax++;
        }

        if(chefNonActive > 0){
            System.out.println("assigner le chef à une tache: 5");
            numberActionMax++;
        }
         int action = demandeAction(1, numberActionMax);
        return action;

    }

    private int proposeMenuEnFonctionDuPersonnage(int choix){
        int action = 0;
         switch (choix) {
            case 1:
                // proposer menu pour villageois
                    action = afficherMenuPourVillageois();
                break;

            case 2:
                // proposer menu pour soldat
                break;

             case 3:
                // proposer menu pour artisans
                break;

            case 4:
                // proposer menu pour eclaireur
                break;

            case 5:
                // proposer menu pour chef
                break;

            default:
                System.out.println("action inconnue.");
         }

         return action;
    }

    private int afficherMenuPourVillageois() {

        System.out.println("construire une maison: 1");
        System.out.println("aller en foret: 2");
        System.out.println("aller à la mine: 3");
        System.out.println("construire une caserne: 4");
        System.out.println("construire une ferme: 5");
        System.out.println("construire une atelier: 6");
        System.out.println("construire une mur de défense: 7");
        System.out.println("former des soldats: 8");
        System.out.println("former des éclaireurs: 9");
        System.out.println("former des artisans: 10");
        System.out.println("finir la journée: 11");
        System.out.println("quitter le jeu: 12");

        int action = demandeAction(1, 12);
        return action;

    }

    private int afficherMenuPourSoldats() {

        System.out.println("envoyer au mur: 1");
        System.out.println("envoyer en mission externe: 2");
        System.out.println("finir la journée: 3");
        System.out.println("quitter le jeu: 4");

        int action = demandeAction(1, 4);
        return action;

    }

    private int afficherMenuPourArtisans() {

        System.out.println("améliorer les batiments: 1");
        System.out.println("produire des outils: 2");
        System.out.println("finir la journée: 3");
        System.out.println("quitter le jeu: 4");

        int action = demandeAction(1, 4);
        return action;

    }

    private int afficherMenuPourEclaireurs() {

        System.out.println("aller explorer: 1");
        System.out.println("finir la journée: 2");
        System.out.println("quitter le jeu: 3");

        int action = demandeAction(1, 3);
        return action;

    }

    private int afficherMenuPourChef() {

        return afficherMenuPourSoldats();

    }

    private void faitAction(int action) {
        switch (action) {
            case 1:
                // construire maison
                maison.construire(this);
                break;

            case 2:
                // aller en foret
                foret.villageoisVoulantPartirEnForet(1, this);
                break;

             case 3:
                // aller à la mine
                mine.ajouteVillageoisDansLesMines(1, this);
                break;

            case 4:
                // construire caserne
                caserne.construire(this);
                break;

            case 5:
                // construire ferme

                ferme.construire(this);

                break;

            case 6:
                // construire atelier
                atelier.construire(this);

                break;

            case 7:
                // construire mur de defense
                murDeDefense.construire(this);
                break;

            case 8:
                // former des soldats
                caserne.ajouteVillageoisEnFormation(1, "soldat", this);
                break;

            case 9:
                // former des éclaireurs
                caserne.ajouteVillageoisEnFormation(1, "eclaireur", this);
                break;

            case 10:
                // former des artisans
                atelier.villageoisEnFormation(1, this);
                break;

            case 11:
                // construire mur de defense
                journeeTerminee = true;
                break;

            case 12:
                isOver = true;
                System.out.println("On quitte le jeu");
                break;

            default:
                System.out.println("action inconnue.");
        }
    }

    private void updateRessources() {
        // ressources mine
        int[] ressourcesRecupDansMines = mine.obtenirRessourcesDuJour();
        int pierreTrouveDansMine = ressourcesRecupDansMines[0];
        int ferTrouveDansMine = ressourcesRecupDansMines[1];
        ressource.ajoutePierre(pierreTrouveDansMine);
        ressource.ajouteFer(ferTrouveDansMine);

        // ressources ferme
        int nourritureProduite = ferme.recupereNourritureProduite();
        ressource.ajouteNourriture(nourritureProduite);

        // ressources foret
        int[] ressourcesTrouveesEnForet = foret.recuperereRessourcesTrouvees();
        int nourritureTrouverEnForet = ressourcesTrouveesEnForet[0];
        int boisTrouverEnForet = ressourcesTrouveesEnForet[1];
        int villageoisTrouverEnForet = ressourcesTrouveesEnForet[2];
        int minesTrouverEnForet = ressourcesTrouveesEnForet[3];
        ressource.ajouteNourriture(nourritureTrouverEnForet);
        ressource.ajouteBois(boisTrouverEnForet);
        int population = getPopulation();
        if (villageoisTrouverEnForet != 0) {
            if ((villageoisTrouverEnForet + population) > capacite) {
                System.out.println("Vous n'avez pas assez de place");
            } else {
                // on augmente les villageois
                villageoisNonActive += villageoisTrouverEnForet;
                System.out.println("Super vous avez augmenté votre population");
            }
        }

        if (minesTrouverEnForet > 0) {
            mine.ajouteAuTotalDeMines(minesTrouverEnForet);
        }

    }

    private void lesVillageoisOntFiniLeurtache() {
        // maison
        finisDeConstruireLesMaisons();

        // foret
        lesVillageoisSontDeRetourDeLaForet();

        // ferme
        finisDeConstruireLesFermes();

        // atelier
        finisDeConstruireLesAteliers();

        formationAtelierTerminee();

        // mine
        lesVillageoisRentreDeLaMine();

        // mur
        finisDeConstruireLeMur();

        // caserne
        finisDeConstruireLesCasernes();
        formationALaCaserneTermine();
    }

    private void formationALaCaserneTermine() {
        int[] villageoisFormes = caserne.recupereVillageoisEnFormation();
        int soldats = villageoisFormes[0];
        int eclaireurs = villageoisFormes[1];
        soldatsNonActive += soldats;
        villageoisActive -= soldats;
        villageoisNonActive -= soldats;
        eclaireursNonActive += eclaireurs;
        villageoisActive -= eclaireurs;
        villageoisNonActive -= eclaireurs;
    }

    private void finisDeConstruireLesCasernes() {        
        int villageoisQuiOntFinisDeConstruireCasernes = caserne.recupereVillageoisQuiOntFiniLaConstructionDesCaserne();
        villageoisActive -= villageoisQuiOntFinisDeConstruireCasernes;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireCasernes;
    }

    private void finisDeConstruireLesMaisons() {
        int villageoisQuiOntFinisDeConstruireMaisons = maison.recupereVillageoisQuiConstruisentUneMaison();
        villageoisActive -= villageoisQuiOntFinisDeConstruireMaisons;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireMaisons;
    }

    private void lesVillageoisSontDeRetourDeLaForet() {
        int villageoisDeRetour = foret.recupereVillageoisPartientEnForet();
        villageoisActive -= villageoisDeRetour;
        villageoisNonActive += villageoisDeRetour;
    }

    private void finisDeConstruireLesFermes() {
        int villageoisQuiOntFinisDeConstruireFermes = ferme.recupereVillageoisQuiConstruisentUneFerme();
        villageoisActive -= villageoisQuiOntFinisDeConstruireFermes;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireFermes;
    }

    private void finisDeConstruireLesAteliers() {
        // afaire
        int villageoisQuiOntFinisDeConstruireAteliers = atelier.recupereVillageoisQuiOntConstruitAtelier();
        villageoisActive -= villageoisQuiOntFinisDeConstruireAteliers;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireAteliers;
    }

    private void formationAtelierTerminee() {
        int villageoisFormes = atelier.recupereVillageoisEnFormation();
        villageoisActive -= villageoisFormes;
        artisansNonActive += villageoisFormes;
    }

    private void lesVillageoisRentreDeLaMine() {
        int villageoisDeRetourDeLaMine = mine.recupereVillageoisPartiALaMine();
        villageoisActive -= villageoisDeRetourDeLaMine;
        villageoisNonActive += villageoisDeRetourDeLaMine;
    }

    private void finisDeConstruireLeMur() {
        int villageoisQuiOntFinisDeConstruireLeMur = murDeDefense.recupererVillageois();
        villageoisActive -= villageoisQuiOntFinisDeConstruireLeMur;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireLeMur;
    }

    private void desGensVeulentrejoindreLeVillage() {
        // il y a un soucis ici. si 0 villageois il nous demande quand meme
        double tauxDeCroissance = 0.2;
        int population = getPopulation();
        int inconnus = (int) tauxDeCroissance * population * (1 - (population / capacite));

        if (inconnus > 0) {
            System.out.printf("Il y a %d personnes qui veulent rejoindre votre village.", inconnus);
            System.out.println("Voulez-vous les accepter? ");
            boolean accepte = demandeOuiOuNon();

            if (accepte) {
                if ((inconnus + population) > capacite) {
                    System.out.println("Vous n'avez pas assez de place");
                } else {
                    // on augmente les villageois
                    villageoisNonActive += inconnus;
                    System.out.println("Super vous avez augmenté votre population");
                }
            }
        }

    }

    private void lesVillageoisMangent(){
        int nourritureConsommees = getPopulation();
        ressource.retirerNourriture(nourritureConsommees);
    }

    
    private void YEnAvaitIlPourToutLeMonde(){
        int nourritureRestante = ressource.getNourriture();
        
        if(nourritureRestante < 0){
           
            for(int i=nourritureRestante; i<0; i++){
                if(villageoisNonActive > 0){
                    villageoisNonActive--;
                    ressource.ajouteNourriture(1);
                    continue;
                }else if(artisansNonActive > 0){
                    artisansNonActive--;
                    ressource.ajouteNourriture(1);
                    continue;
                }else if(soldatsNonActive > 0){
                    soldatsNonActive--;
                    ressource.ajouteNourriture(1);
                    continue;
                }else if( eclaireursNonActive > 0){
                    eclaireursNonActive--;
                    ressource.ajouteNourriture(1);
                    continue;
                }else{
                    chefNonActive--;
                    ressource.ajouteNourriture(1);
                }
            }
            
        }
   
        int population = getPopulation();
        if(population <= 0){
            isOver = true;
            System.out.println("tout le monde est mort de faim.");
        }
    }

    private void ajoutJour() {
        jour++;
    }

    private int getPopulation() {
        return villageoisNonActive + soldatsNonActive + eclaireursNonActive + artisansNonActive + chefNonActive;
    }

    public int getPierre() {
        return ressource.getPierre();
    }

    public int getBois() {
        return ressource.getBois();
    }

    public void retirePierre(int quantite) {
        ressource.retirePierre(quantite);
    }

    public void retireBois(int quantite) {
        ressource.retireBois(quantite);
    }

    public int getVillageoisNonActive() {
        return villageoisNonActive;
    }

    public void deplaceVillageoisNonActifVersActif(int quantite) {
        villageoisNonActive -= quantite;
        villageoisActive += quantite;
        System.out.println("villageois non actif = "+ villageoisNonActive);
        System.out.println("villageois actif = "+ villageoisActive);
    }

    private int demandeAction(int min, int max) {
        boolean isValid = false;
        int action = 1;
        while (!isValid) {
            System.out.print("action: ");
            try {
                int number = scanner.nextInt();
                scanner.nextLine();
                if (number >= min && number <= max) {
                    isValid = true;
                    action = number;
                } else {
                    System.out.printf("un numéro entre %d et %d\n", min, max);
                }

            } catch (Exception e) {
                System.out.printf("un numéro entre %d et %d\n", min, max);
            }
        }

        return action;

    }

    private boolean demandeOuiOuNon() {
        boolean isValid = false;
        boolean reponse = false;
        while (!isValid) {
            System.out.print("vous acceptez?(O/N): ");
            try {
                String accepte = scanner.nextLine();
                String accepteUppercase = accepte.toUpperCase();

                if (accepteUppercase.equals("O") && accepteUppercase.equals("N")) {

                    if (accepteUppercase.equals("O")) {
                        reponse = true;
                    } else {
                        reponse = false;
                    }

                    isValid = true;
                } else {
                    System.out.println("O pour Oui, N pour Non");
                }

            } catch (Exception e) {
                System.out.println("O pour Oui, N pour Non");
            }
        }

        return reponse;

    }

    public void setCapacite(int nouvelleCapacite) {
        capacite = nouvelleCapacite;
    }
}
