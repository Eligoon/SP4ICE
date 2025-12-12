package creatures.attributes;

public class Stats {
    /* The Stats class holds the basic numerical attributes for any Creature:
     * current health, maximum health, strength, dexterity, intelligence. */
    private int maxHealth;         // Maximum health
    private int currentHealth;     // Current health, decreases with damage
    private int strength;
    private int dexterity;
    private int intelligence;

    // --- Constructor to set the initial stats ---
    public Stats(int maxHealth, int strength, int dexterity, int intelligence) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth; // Start at full health
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    // --- Getters ---
    public int getCurrentHealth() {
        return currentHealth;
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
    public void setCurrentHealth(int value) {
        if (value > maxHealth) {
            this.currentHealth = maxHealth; // Cannot exceed max health
        } else if (value < 0) {
            this.currentHealth = 0;         // Cannot go below 0
        } else {
            this.currentHealth = value;     // Normal case
        }
    }

    public void setMaxHealth(int value) {
        this.maxHealth = value;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
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

    // Apply a modifier to one of the stats
    public void applyModifier(String stat, int modifier) {
        switch (stat.toLowerCase()) {
            case "health":
                setCurrentHealth(this.currentHealth + modifier);
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

    // Take damage
    public void takeDamage(int damage) {
        setCurrentHealth(currentHealth - damage);
    }

    // Heal by a fixed amount
    public void heal(int amount) {
        setCurrentHealth(currentHealth + amount);
    }
}
