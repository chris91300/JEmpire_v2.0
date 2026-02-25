package models;

import java.util.Scanner;

import models.batiments.Caserne;
import models.batiments.Ferme;
import models.batiments.Mine;
import models.ressources.Ressource;

public class Village {
    static Scanner scanner = new Scanner(System.in);
    private int capacite = 4;
    private int villageoisNonActive = 4;
    private int villageoisActive = 0;
    private int soldatsNonActive =0;
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
    private Ressource ressource = new Ressource();
    private int jour = 1;
    private boolean isOver = false;


   public void start(){
        while(!isOver){
            afficherJour();
            ressource.afficheRessources();
            int action = afficherMenu();
            faitAction(action);

            updateRessources();
            formationALaCaserneTermine();
            desGensVeulentrejoindreLeVillage();
            ajoutJour();
        }
    }

    private void afficherJour(){
        System.out.println("****************************************");
        System.out.printf("\t\tJOUR %d\t\t\n", jour);
        System.out.println("****************************************");

    }

    private int afficherMenu(){
        
        System.out.println("que fait on aujourd'hui?");
        System.out.println("construire une maison: 1");
        System.out.println("construire une caserne: 2");
        System.out.println("construire une ferme: 3");
        System.out.println("construire une mine: 4");
        System.out.println("construire une atelier: 5");        
        System.out.println("construire une mur de défense: 6");
        System.out.println("quitter: 7");
        
        int action = demandeAction(1, 7);
         return action;
       

    }


    private void faitAction(int action){
        switch(action){
            case 1:
                // construire maison
                System.out.println("construit maison");
                //mainson.build(this);
                break;
            
             case 2:
                // construire caserne
                System.out.println("construit caserne");
                caserne.construire(this);
                //caserne.build(this);
                break;
            
             case 3:
                // construire ferme
                System.out.println("construit ferme");
                //ferme.build(this);
                break;

             case 4:
                // construire mine
                System.out.println("construit mine");
                //mine.build(this);
                break;

             case 5:
                //construire atelier
                System.out.println("construit atelier");
                //atelier.build(this);
                break;

             case 6:
                //construire mur de defense
                System.out.println("construit mur de defense");
                //mur.build(this);
                break;

             case 7:
                isOver = true;
                System.out.println("On quitte le jeu");
                break;

            default:
            System.out.println("action inconnue.");
        }
    }
   

    private void updateRessources(){
        int[] ressourcesRecupDansMines = mine.obtenirRessourcesDuJour();
        int pierreTrouve = ressourcesRecupDansMines[0];
        int ferTrouve = ressourcesRecupDansMines[1];
        ressource.ajoutePierre(pierreTrouve);
        ressource.ajouteFer(ferTrouve);
        // recuperer bois
        // recuperer fer
    }

    private void formationALaCaserneTermine(){
        int[] villageoisFormes = caserne.recupereVillageoisFormes();
        int soldats = villageoisFormes[0];
        int eclaireurs = villageoisFormes[1];
        soldatsNonActive += soldats;
        eclaireursNonActive += eclaireurs;
    }

    private void desGensVeulentrejoindreLeVillage(){
        double tauxDeCroissance = 0.2;
        int population = getPopulation();
        int inconnus = (int) tauxDeCroissance * population *(1 -(population / capacite));
        System.out.printf("Il y a %d personnes qui veulent rejoindre votre village.", inconnus);
        System.out.println("Voulez-vous les accepter? ");
        boolean accepte = demandeOuiOuNon();
        
        if(accepte){
            if((inconnus + population) > capacite){
                System.out.println("Vous n'avez pas assez de place");
            }else{
                // on augmente les villageois
                villageoisNonActive += inconnus;
                System.out.println("Super vous avez augmenté votre population");
            }
        }
    }

    private void ajoutJour(){
        jour++;
    }

    private int getPopulation(){
        return villageoisNonActive + soldatsNonActive + eclaireursNonActive + artisansNonActive + chefNonActive;
    }


    public int getPierre(){
        return ressource.getPierre();
    }

    public int getBois(){
        return ressource.getBois();
    }

    public void retirePierre(int quantite){
        ressource.retirePierre(quantite);
    }

     public void retireBois(int quantite){
        ressource.retireBois(quantite);
    }

    public int getVillageoisNonActive(){
        return villageoisNonActive;
    }

    public void deplaceVillageois(int quantite){
        villageoisNonActive -= quantite;
        villageoisActive += quantite;
    }

    private int demandeAction(int min, int max){
        boolean isValid = false;
        int action = 1;
        while(!isValid){
            System.out.print("action: ");
            try{
                int number = scanner.nextInt();
                scanner.nextLine();
                if(number > min && number <= max){
                    isValid = true;
                    action = number;
                }else{
                    System.out.printf("un numéro entre %d et %d\n", min, max);
                }
                
            }catch(Exception e){
                System.out.printf("un numéro entre %d et %d\n", min, max);
            }
        }

        return action;

    }

    private boolean demandeOuiOuNon(){
        boolean isValid = false;
        boolean reponse = false;
        while(!isValid){
            System.out.print("vous acceptez?(O/N): ");
            try{
                String accepte = scanner.nextLine();
                String accepteUppercase = accepte.toUpperCase();
                
                if(accepteUppercase.equals("O") && accepteUppercase.equals("N")){
                    
                    if(accepteUppercase.equals("O")){
                        reponse = true;
                    }else{
                        reponse = false;
                    }

                    isValid = true;
                }else{
                    System.out.println("O pour Oui, N pour Non");
                }
                
            }catch(Exception e){
                System.out.println("O pour Oui, N pour Non");
            }
        }

        return reponse;

    }

    public void setCapacite(int nouvelleCapacite){
        capacite = nouvelleCapacite;
    }
}
