package creatures;

import creatures.attributes.Race;
import creatures.attributes.Stats;

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

    // Apply damage to the creature
    public void takeDamage(int damage) {
        stats.setCurrentHP(stats.getCurrentHP() - damage);
        if (stats.getCurrentHP() <= 0) {
            stats.setCurrentHP(0);
            isDead = true;
        }
    }

    // Check if creature is alive
    public boolean isAlive() {
        return !isDead;
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
        return stats.getCurrentHP();
    }

    public int getMaxHP() {
        return stats.getMaxHP();
    }

    public boolean getIsDead() {
        return isDead;
    }
}
