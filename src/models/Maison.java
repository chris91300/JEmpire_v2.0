package models;

public class Maison {
    private int population;
    private int quantite;

    public Maison (int population, int quantite){
        this.population = population;
        this.quantite = quantite;
    };

   public int getQuantite() {
       return quantite;
   }
   
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
