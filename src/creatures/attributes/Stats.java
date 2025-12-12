package creatures.attributes;

public class Stats {
    /* The Stats class holds the basic numerical attributes for any Creature:
     * health, strength, dexterity, intelligence. */
    private int health;
    private int maxHealth;
    private int strength;
    private int dexterity;
    private int intelligence;

    // --- Constructor to set the initial stats ---
    public Stats(int health, int strength, int dexterity, int intelligence) {
        this.health = health;
        this.maxHealth = health; // Store maxHealth
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    // --- Getters ---
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    // --- Setters ---
    public void setHealth(int value) {
        this.health = Math.min(value, maxHealth); // Cannot exceed maxHealth
    }

    public void setStrength(int value) {
        this.strength = value;
    }

    public void setDexterity(int value) {
        this.dexterity = value;
    }

    public void setIntelligence(int value) {
        this.intelligence = value;
    }

    // Applies a modifier to one of the stats (health, strength, dexterity, intelligence)
    public void applyModifier(String stat, int modifier) {
        switch (stat.toLowerCase()) {
            case "health":
                setHealth(this.health + modifier);
                break;
            case "strength":
                setStrength(this.strength + modifier);
                break;
            case "dexterity":
                setDexterity(this.dexterity + modifier);
                break;
            case "intelligence":
                setIntelligence(this.intelligence + modifier);
                break;
            default:
                System.out.println("Invalid stat: " + stat);
        }
    }

    // Reduce health by damage amount
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    // Heal by a fixed amount
    public void heal(int amount) {
        setHealth(this.health + amount);
    }
}
