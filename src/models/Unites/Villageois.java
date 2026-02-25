package models.unites;

public class Villageois{
    /**
     * actions possibles:
     * Villageaois voguer, reposer, construire
     * Soldat: reste, va au mur, mission externe
     * Eclaireur: explorer (trouver des gisements), reposer
     * Artisant ameliorer batiment, produit outils (atelier ou chantier), reposer
     * Chef: motive les troupes (+1 ou 2 aux soldat), agit comme soldat en plus perf
     */
    static String[] actionsPossibles = {
        "voguer",
        "reposer",
        "construire",
        "rester",
        "aller au mur",
        "mission externe",
        "explorer",
        "ameliorer",
        "produire",
        "motiver"
    };
    protected int sante = 10;
    protected int quantite = 4;
    protected String nom = "Villageois";
    protected String action = "reposer";


   /* public Villageois(String nom){
        this.nom = nom;
    }*/

    public Villageois(String nom, int quantite, int sante){
        this.nom = nom;
        this.sante = sante;
        this.quantite = quantite;
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        boolean isPossible = false;
        for(String actionPossible: actionsPossibles){
            if(actionPossible.equals(action)){
                isPossible = true;
                break;
            }
        }
        if(isPossible){
            this.action = action;
        }else{
            System.out.println("action inconnue");
        }
        
    }

    public int getSante() {
        return sante;
    }

    public void setSante(int sante) {
        this.sante = sante;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

