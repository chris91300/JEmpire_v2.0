package models;

public class Ressources {
    private String nom;
    private int quantite;
    
    public Ressources (String nom, int quantite) {
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
