package creatures;

import creatures.attributes.Race;
import creatures.attributes.Stats;
import util.TextUI;
import world.Story;

/* The Creature class represents any living being in the game.
 * This is a superclass for Player and NPC. */
public class Creature {
    protected String name;             // The creature's display name
    protected String description;      // Description of the creature
    protected Race race;               // The creature's race (Human, Elf, Orc, Dwarf)
    protected Stats stats;             // The creature's stats (health, strength, dexterity, intelligence)
    protected boolean isDead = false;  // Tracks if creature is dead

    // Constructor for NPC or general creature
    public Creature(String name, Race race, Stats stats) {
        this.name = name;
        this.race = race;
        this.stats = stats;
    }

    // In Creature.java
    public void interactWithPlayer(Player player, Story story, TextUI ui) {
        ui.displayMsg("This creature cannot be interacted with.");
    }

    // Apply damage to the creature
    public void takeDamage(int damage) {
        stats.setCurrentHealth(stats.getCurrentHealth() - damage);
        if (stats.getCurrentHealth() <= 0) {
            stats.setCurrentHealth(0);
            isDead = true;
        }
    }

    // Check if creature is alive
    public boolean isAlive() {
        return !isDead;
    }

    // Setter for dead status
    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public Stats getStats() {
        return stats;
    }

    public int getCurrentHP() {
        return stats.getCurrentHealth();
    }

    public int getMaxHP() {
        return stats.getMaxHealth();
    }

    public boolean getIsDead() {
        return isDead;
    }
}
