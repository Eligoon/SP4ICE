
package creatures.attributes;

public class Stats {
    /*The Stats class holds the basic numerical attributes for any Creature:
     * health, strength, dexterity, intelligence.*/
    private int health;
    private int strength;
    private int dexterity;
    private int intelligence;

    // --- Constructor to set the initial stats ---
    public Stats(int health, int strength, int dexterity, int intelligence){
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    // --- Getters ---
    public int getHealth(){
        return health;
    }
    public int getStrength(){
        return strength;
    }
    public int getDexterity(){
        return dexterity;
    }
    public int getIntelligence(){
        return intelligence;
    }

    //--- Setters ---

    public void setHealth(int value) {
        this.health = value;
    }
    public void setStrength(int value){
        this.strength = value;
    }
    public void setDexterity(int value){
        this.dexterity = value;
    }
    public void setIntelligence(int value){
        this.intelligence = value;
    }

    //Applies a modifier to one of the stats (health, strength, dexterity, intelligence)
    public void applyModifier(String stat, int modifier){
        //TODO: Add logic
    }
}