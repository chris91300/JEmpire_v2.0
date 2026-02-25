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
                int action = afficherMenu();
                faitAction(action); 
                if(isOver){
                    break;
                }               
            }
            if(isOver){
                break;
            }
            updateRessources();
            lesVillageoisOntFiniLeurtache();            
            desGensVeulentrejoindreLeVillage();
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
        System.out.printf("\t\tVILLAGEOIS DISPONIBLE %d\t\t\n", villageoisNonActive);
        System.out.println("****************************************");
    }

    private int afficherMenu() {

        System.out.println("que fait on aujourd'hui?");
        System.out.println("construire une maison: 1");
        System.out.println("aller en foret: 2");
        System.out.println("construire une caserne: 3");
        System.out.println("construire une ferme: 4");
        System.out.println("construire une atelier: 5");
        System.out.println("construire une mur de défense: 6");
        System.out.println("finir la journée: 7");
        System.out.println("quitter le jeu: 8");

        int action = demandeAction(1, 8);
        return action;

    }

    private void faitAction(int action) {
        switch (action) {
            case 1:
                // construire maison
                maison.construire(this);
                break;

            case 2:
                // construire maison
                foret.villageoisVoulantPartirEnForet(1, this);
                break;

            case 3:
                // construire caserne
                caserne.construire(this);
                break;

            case 4:
                // construire ferme

                ferme.construire(this);

                break;

            case 5:
                // construire atelier
                atelier.construire(this);

                break;

            case 6:
                // construire mur de defense
                murDeDefense.construire(this);
                break;

            case 7:
                // construire mur de defense
                journeeTerminee = true;
                break;

            case 8:
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
        if(villageoisTrouverEnForet != 0){
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

    private void lesVillageoisOntFiniLeurtache(){
        //maison
        finisDeConstruireLesMaisons();
        
        //foret
        lesVillageoisSontDeRetourDeLaForet();
        //ferme
        finisDeConstruireLesFermes();
        //atelier
        finisDeConstruireLesAteliers(); // afiinir
        formationAtelierTerminee();
        //mine
        lesVillageoisRentreDeLaMine();
        //mur
        finisDeConstruireLeMur();
        //caserne
        finisDeConstruireLesCasernes();
        formationALaCaserneTermine();
    }

    private void formationALaCaserneTermine() {
        int[] villageoisFormes = caserne.recupereVillageoisEnFormation();
        int soldats = villageoisFormes[0];
        int eclaireurs = villageoisFormes[1];
        soldatsNonActive += soldats;
        soldatsActive -= soldats;
        eclaireursNonActive += eclaireurs;
        eclaireursActive -= eclaireurs;
    }

    private void finisDeConstruireLesCasernes(){
        // afaire
        int villageoisQuiOntFinisDeConstruireCasernes = caserne.recupereVillageoisQuiOntFiniLaConstructionDesCaserne();
        villageoisActive -= villageoisQuiOntFinisDeConstruireCasernes;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireCasernes;
    }

    private void finisDeConstruireLesMaisons(){
        int villageoisQuiOntFinisDeConstruireMaisons = maison.recupereVillageoisQuiConstruisentUneMaison();
        villageoisActive -= villageoisQuiOntFinisDeConstruireMaisons;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireMaisons;
    }

    private void lesVillageoisSontDeRetourDeLaForet(){
        int villageoisDeRetour = foret.recupereVillageoisPartientEnForet();
        villageoisActive -= villageoisDeRetour;
        villageoisNonActive += villageoisDeRetour;
    }

    private void finisDeConstruireLesFermes(){
        int villageoisQuiOntFinisDeConstruireFermes = ferme.recupereVillageoisQuiConstruisentUneFerme();
        villageoisActive -= villageoisQuiOntFinisDeConstruireFermes;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireFermes;
    }

    private void finisDeConstruireLesAteliers(){
        // afaire
        int villageoisQuiOntFinisDeConstruireAteliers = atelier.recupereVillageoisQuiOntConstruitAtelier();
        villageoisActive -= villageoisQuiOntFinisDeConstruireAteliers;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireAteliers;
    }

    private void formationAtelierTerminee(){
        int villageoisFormes = atelier.recupereVillageoisEnFormation();
        villageoisActive -= villageoisFormes;
        villageoisNonActive += villageoisFormes;
    }

    private void lesVillageoisRentreDeLaMine(){
        int villageoisDeRetourDeLaMine = mine.recupereVillageoisPartiALaMine();
        villageoisActive -= villageoisDeRetourDeLaMine;
        villageoisNonActive += villageoisDeRetourDeLaMine;
    }

    private void finisDeConstruireLeMur(){
        int villageoisQuiOntFinisDeConstruireLeMur = murDeDefense.recupererVillageois();
        villageoisActive -= villageoisQuiOntFinisDeConstruireLeMur;
        villageoisNonActive += villageoisQuiOntFinisDeConstruireLeMur;
    }

    private void desGensVeulentrejoindreLeVillage() {
        // il y a un soucis ici. si 0 villageois il nous demande quand meme
        double tauxDeCroissance = 0.2;
        int population = getPopulation();
        int inconnus = (int) tauxDeCroissance * population * (1 - (population / capacite));
        
        if(inconnus > 0){
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
