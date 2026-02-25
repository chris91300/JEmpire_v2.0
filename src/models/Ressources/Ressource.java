package models.ressources;

public class Ressource {
    private int nourriture = 10;
    private int bois = 10;
    private int fer = 0;
    private int pierre = 0;
    private int or= 0;


    public void afficheRessources(){
        System.out.printf(
            "nourriture: %d; bois: %d; pierre: %d; fer: %d; or: %d\n",
            getNourriture(),
            getBois(),
            getPierre(),
            getFer(),
            getOr()

        );
    } 

    public void ajouteNourriture(int quantite){
        int quantiteNourriture = getNourriture();
        setNourriture( quantiteNourriture + quantite);
    }

     public void ajouteBois(int quantite){
        int quantiteBois = getBois();
        setBois( quantiteBois + quantite);
    }

     public void ajoutePierre(int quantite){
        int quantitePierre = getPierre();
        setPierre( quantitePierre + quantite);
    }

     public void ajouteFer(int quantite){
        int quantiteFer = getFer();
        setFer( quantiteFer + quantite);
    }

     public void ajouteOr(int quantite){
        int quantiteOr = getOr();
        setOr( quantiteOr + quantite);
    }

    public int getNourriture() {
        return nourriture;
    }
    public void setNourriture(int nourriture) {
        this.nourriture = nourriture;
    }
    public int getBois() {
        return bois;
    }
    public void setBois(int bois) {
        this.bois = bois;
    }
    public int getFer() {
        return fer;
    }
    public void setFer(int fer) {
        this.fer = fer;
    }
    public int getPierre() {
        return pierre;
    }

    public void retirePierre(int quantite){
        this.pierre -= quantite;
    }

    public void setPierre(int pierre) {
        this.pierre = pierre;
    }
    public int getOr() {
        return or;
    }
    public void setOr(int or) {
        this.or = or;
    }
    
   
        
  
}
