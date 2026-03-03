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
import models.unites.Artisan;
import models.unites.Chef;
import models.unites.Eclaireur;
import models.unites.Soldat;
import models.unites.Villageois;

public class Village {
    static Scanner scanner = new Scanner(System.in);
    private int capacite = 4;
    Villageois villageois = new Villageois();
    Soldat soldat = new Soldat();
    Eclaireur eclaireur = new Eclaireur();
    Artisan artisan = new Artisan();
    Chef chef = new Chef();
    private Maison maison = new Maison();
    private Caserne caserne = new Caserne();
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
                boolean isOk;
                do {
                    int choixDuMenu = afficherMenuGlobal();
                    int action = proposeMenuEnFonctionDuPersonnage(choixDuMenu);
                    isOk = faitAction(action);
                } while(!isOk);
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
            yEnAvaitIlPourToutLeMonde();
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
            villageois.getTotalDisponible(),
            soldat.getTotalDisponible(),
            eclaireur.getTotalDisponible(),
            artisan.getTotalDisponible(),
            chef.getTotalDisponible()
        );
        System.out.println("****************************************");
    }

    private int afficherMenuGlobal(){
        int numberActionMax = 1;
        System.out.println("que fait on aujourd'hui?");
        System.out.println("assigner des villageois à une tache: 1");

        if(soldat.getTotalDisponible() > 0){
            System.out.println("assigner des soldats à une tache: 2");
            numberActionMax++;
        }

        if(artisan.getTotalDisponible() > 0){
            System.out.println("assigner des artisans à une tache: 3");
            numberActionMax++;
        }

        if(eclaireur.getTotalDisponible() > 0){
            System.out.println("assigner des éclaireurs à une tache: 4");
            numberActionMax++;
        }

        if(chef.getTotalDisponible() > 0){
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

    private boolean faitAction(int action) {
        int villageoisAffecter = 1;
        boolean isOk = true;
        try{
            switch (action) {
                case 1:
                    // construire maison
                    villageoisAffecter = maison.getQuantiteDeVillageoisNecessaireALaConstruction();
                    villageois.sontIlsDisponible(villageoisAffecter);
                    maison.construire(this);
                    villageois.devientActif(villageoisAffecter);                 
                    break;

                case 2:
                    // aller en foret
                    villageoisAffecter = 1;
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    foret.villageoisVoulantPartirEnForet(1, this);
                    villageois.devientActif(villageoisAffecter);                      
                    break;

                case 3:
                    // aller à la mine
                    villageoisAffecter = 1;
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    mine.ajouteVillageoisDansLesMines(1, this);
                    villageois.devientActif(villageoisAffecter);   
                    break;

                case 4:
                    // construire caserne
                    villageoisAffecter = caserne.getQuantiteDeVillageoisNecessaireALaConstruction();
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    caserne.construire(this);
                    villageois.devientActif(villageoisAffecter);   
                    break;

                case 5:
                    // construire ferme
                    villageoisAffecter = caserne.getQuantiteDeVillageoisNecessaireALaConstruction();
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    ferme.construire(this);
                    villageois.devientActif(villageoisAffecter);   
                    break;

                case 6:
                    // construire atelier
                    villageoisAffecter = caserne.getQuantiteDeVillageoisNecessaireALaConstruction();
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    atelier.construire(this);
                    villageois.devientActif(villageoisAffecter);   
                    break;

                case 7:
                    // construire mur de defense
                    villageoisAffecter = murDeDefense.getQuantiteDeVillageoisNecessaireALaConstruction();
                    villageois.sontIlsDisponible(villageoisAffecter);                                 
                    murDeDefense.construire(this);
                    villageois.devientActif(villageoisAffecter);   
                    break;

                case 8:
                    // former des soldats
                    villageoisAffecter = 1;
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    caserne.ajouteVillageoisEnFormation(1, "soldat", this);
                    villageois.devientActif(villageoisAffecter);
                    break;

                case 9:
                    // former des éclaireurs
                    villageoisAffecter = 1;
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    caserne.ajouteVillageoisEnFormation(1, "eclaireur", this);
                    villageois.devientActif(villageoisAffecter); 

                case 10:
                    // former des artisans
                    villageoisAffecter = 1;
                    villageois.sontIlsDisponible(villageoisAffecter);                    
                    atelier.villageoisEnFormation(1, this);
                    villageois.devientActif(villageoisAffecter); 
                    break;

                case 11:
                    journeeTerminee = true;
                    break;

                case 12:
                    isOver = true;
                    System.out.println("On quitte le jeu");
                    break;

                default:
                    System.out.println("action inconnue.");
                    isOk = false;
            }
        }catch(Error e){
            System.out.println(e.getMessage());
            isOk = false;
        }

        return isOk;
        
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
                villageois.ajoutDeNouveauVillageois(villageoisTrouverEnForet);
                
            }
        }

        if (minesTrouverEnForet > 0) {
            mine.ajouteAuTotalDeMines(minesTrouverEnForet);
        }

    }

    private void lesVillageoisOntFiniLeurtache() {
        villageois.rentreDeLeurTache();
        soldat.rentreDeLeurTache();
        eclaireur.rentreDeLeurTache();
        artisan.rentreDeLeurTache();
        chef.rentreDeLeurTache();

        maison.lesVillageoisSeReposent();
        foret.lesVillageoisSeReposent();
        caserne.lesVillageoisSeReposent();
        ferme.lesVillageoisSeReposent();
        mine.lesVillageoisSeReposent();
        atelier.lesVillageoisSeReposent();
        murDeDefense.lesVillageoisSeReposent();
    }

    private void desGensVeulentrejoindreLeVillage() {
        
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
                    villageois.ajoutDeNouveauVillageois(inconnus);
                    System.out.println("Super vous avez augmenté votre population");
                }
            }
        }

    }

    private void lesVillageoisMangent(){
        int nourritureConsommees = getPopulation();
        ressource.retirerNourriture(nourritureConsommees);
    }

    
    private void yEnAvaitIlPourToutLeMonde(){
        int nourritureRestante = ressource.getNourriture();
        
        if(nourritureRestante < 0){
           
            for(int i=nourritureRestante; i<0; i++){
                if(villageois.getTotalDisponible() > 0){
                    villageois.meurtDeFain();
                    ressource.ajouteNourriture(1);
                    continue;
                }else if(artisan.getTotalDisponible() > 0){
                    artisan.meurtDeFain();
                    ressource.ajouteNourriture(1);
                    continue;
                }else if(soldat.getTotalDisponible() > 0){
                    soldat.meurtDeFain();
                    ressource.ajouteNourriture(1);
                    continue;
                }else if( eclaireur.getTotalDisponible() > 0){
                    eclaireur.meurtDeFain();
                    ressource.ajouteNourriture(1);
                    continue;
                }else{
                    chef.meurtDeFain();
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
        return villageois.getTotalDisponible() + soldat.getTotalDisponible() + eclaireur.getTotalDisponible() + artisan.getTotalDisponible() + chef.getTotalDisponible();
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
