package creatures;

import collectibles.Item;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import world.Location;

/* The Creature class represents any living being in the game.
* This is a superclass for Player and NPC. */
public class Creature {
    protected String name; //The creatures display name
    protected String description; //
    protected Race race; // The creatures tace (Human, Elf, Orc, Dwarf)
    protected Stats stats; // Thea creatures stats (health, strength, dexterity and intelligence)

// --- Constructor for creating a Creature ---
    public Creature(String name, Race race, Stats stats) {

        this.name = name;
        this.race = race;
        this.stats = stats;
    }

    public void interact(){ // Allows creature to interact with something in the game world.
         //TODO
    }
    public void fight (Creature target) { //Handles combat with another creature
         //TODO
    }

    public void pickUpItem (Item item) { // Adds a item to the creatures inventory (for Player)
        //TODO
    }

// --- Getters ---
    public String getName () {
        return name;
    }

    public Race getRace () {
        return race;
    }

    public Stats getStats () {
        return stats;
    }

}
