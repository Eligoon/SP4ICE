package creatures;

import collectibles.Armor;
import collectibles.Item;
import collectibles.Quest;
import collectibles.Weapon;
import creatures.attributes.Inventory;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import world.Location;

import java.util.ArrayList;
import java.util.List;

/* The Player class represents the main character controlled by the user.
 * It extends Creature and inherits name, race and stats. */

public class Player extends Creature {
    // Player choose a class
    private Class characterClass;
    //The players inventory holding items
    private Inventory inventory;
    //A list of quests the player has accepted
    private List<Quest> questLog;

    // --- Constructor for new player object ---
    public Player(String name, Race race, Class characterClass, Stats stats) {
        super(name, race, stats);

        this.characterClass = characterClass;

        // create empty inventory for the player
        this.inventory = new Inventory();
        // create empty quest log
        this.questLog = new ArrayList<>();
    }

    @Override
    public void interact() { //Interaction placeholder
        //TODO
    }

    public void useItem(Item item) { //called when the player uses an item
        //TODO
    }
    public void equipWeapon(Weapon weapon){ //Equip a weapon from the players inventory
        //TODO
    }
    public void equipArmor(Armor armor){ //Equip armor from the players inventory
        //TODO
    }
    public void addQuest(Quest quest){ //add a quest the players quest log
        questLog.add(quest);
    }
    public void completeQuest(String questID){ // Marks quest as completed
        //TODO
    }

}