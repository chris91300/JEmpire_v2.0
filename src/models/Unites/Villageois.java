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
    /*static String[] actionsPossibles = {
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
    };*/
    protected int sante;
    protected String nom = "Villageois";
    protected String action = "reposer";
    protected int totalNonActif;
    protected int totalActif = 0;

    public Villageois(){
        this.totalNonActif = 4;
        this.sante = 10;
    }

    public Villageois(String nom, int quantite, int sante){
        this.nom = nom;
        this.sante = sante;
        this.totalNonActif = quantite;
    }

    public void devientNonActif(int quantite){
        totalActif -= quantite;
        totalNonActif += quantite;
    }

     public void devientActif(int quantite){
        totalActif += quantite;
        totalNonActif -= quantite;
    }

    public void rentreDeLeurTache(){
        totalNonActif += totalActif;
        totalActif = 0;
    }

    public void ajoutDeNouveauVillageois(int quantite){
        totalNonActif += quantite;
        System.out.println("Super vous avez augmenté votre population");
    }

    public int getTotalDisponible(){
        return totalNonActif;
    }

    public void sontIlsDisponible(int quantite){
        if( quantite > totalNonActif){
            throw new Error("pas assez de villageois de disponible");
        }
    }

    public void meurtDeFain(){
        totalNonActif--;
    }

    public String getAction() {
        return action;
    }

    /*public void setAction(String action) {
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
        
    }*/

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

