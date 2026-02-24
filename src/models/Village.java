package models;

import java.util.Scanner;

import models.batiments.Caserne;
import models.batiments.Ferme;
import models.batiments.Mine;
import models.ressources.Ressource;

public class Village {
    static Scanner scanner = new Scanner(System.in);
    private int capacite = 4;
    private int populationNonActive = 4;
    private int populationActive = 0;
    private Maison maison = new Maison(4, 1);
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
            ajoutJour();
        }
    }

    private void afficherJour(){
        System.out.println("****************************************");
        System.out.printf("\t\tJOUR %d\t\t\n", jour);
        System.out.println("****************************************");

    }

    private int afficherMenu(){
        boolean isValid = false;
        int action = 0;

        while(!isValid){
            System.out.println("que fait on aujourd'hui?");
            System.out.println("construire une maison: 1");
            System.out.println("construire une caserne: 2");
            System.out.println("construire une ferme: 3");
            System.out.println("construire une mine: 4");
            System.out.println("construire une atelier: 5");        
            System.out.println("construire une mur de défense: 6");
            System.out.println("quitter: 7");
            System.out.print("action: ");
            try{
                int number = scanner.nextInt();
                scanner.nextLine();
                if(number > 0 && number < 8){
                    isValid = true;
                    action = number;
                }else{
                    System.out.println("un numéro valide svp");
                }
                
            }catch(Exception e){
                System.out.println("un numéro valide svp");
            }

        }
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

    private void ajoutJour(){
        jour++;
    }


    public int getPierre(){
        return ressource.getPierre();
    }

    public void retirePierre(int quantite){
        ressource.retirePierre(quantite);
    }

    public int getPopulationNonActive(){
        return populationNonActive;
    }

    public void deplaceVillageois(int quantite){
        populationNonActive -= quantite;
        populationActive += quantite;
    }
}
