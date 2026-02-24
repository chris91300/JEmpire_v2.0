public class Soldat extends Unite {
   
    protected int force = 10;
    protected int defense = 5;

    public Soldat(){
        super("Soldat", 0, 50);
    }

    public Soldat(String nom, int quantite, int sante, int force, int defense){
        super("Soldat", quantite, sante);
        this.force = force;
        this.defense = defense;
    }



    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    
}
