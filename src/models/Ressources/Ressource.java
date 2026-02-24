package models.Ressources;

public class Ressource {
    private String nom;
    private int quantite;
    
    public Ressource (String nom, int quantite) {
        this.nom= nom;
        this. quantite = quantite;
    }
        
    public int getQuantite (){
        return this.quantite;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

        
}
