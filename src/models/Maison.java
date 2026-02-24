package models;

public class Maison {
    private int capaciteDuVillage;
    private int quantite;

    public Maison (int capacite, int quantite){
        this.capaciteDuVillage = capacite;
        this.quantite = quantite;
    };

   public int getQuantite() {
       return quantite;
   }
   
    public int getCapaciteDuVillage() {
        return capaciteDuVillage;
    }

    public void setCapaciteDuVillage(int capaciteDuVillage) {
        this.capaciteDuVillage += 4;
    }

    public void setQuantite(int quantite) {
        this.quantite += 1;
    }

}
