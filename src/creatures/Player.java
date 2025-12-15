package creatures;


import collectibles.Item;
import collectibles.Quest;
import creatures.attributes.CharacterClass;
import creatures.attributes.Inventory;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import util.TextUI;

import java.util.ArrayList;
import java.util.List;

/* The Player class represents the main character controlled by the user.
* It extends Creature and inherits name, race and stats. */

public class Player extends Creature {
    // Player choose a class
    private final CharacterClass characterClass;

    // The players inventory holding items
    private Inventory inventory;

    //A list of quests the player has accepted
    private final List<Quest> questLog;

    // Flags to keep track of the story
    private List<String> flags;

    TextUI ui = new TextUI();

    // --- Constructor for new player object ---
    public Player(String name, Race race, CharacterClass characterClass, Stats stats) {
        super(name, race, stats);
        this.characterClass = characterClass;

        // Create empty inventory for the player
        this.inventory = new Inventory(10);

        // create empty quest log
        this.questLog = new ArrayList<>();

        this.flags = new ArrayList<>();

        // Apply race and class bonuses
        race.applyRacialBonuses(this.stats);
        characterClass.applyCharacterClassBonuses(this.stats);

    }


    public void interact() { //Interaction placeholder
        //TODO
    }

    public void addQuest(Quest quest){ //add a quest the players quest log
        questLog.add(quest);
        ui.displayMsg("New quest acquired: " + quest.getQuestName());
        // TODO Add getter in quest?
    }


    // Flag management

    // Add flag to Player flags
    public void addFlag(String flag) {
        if (!flags.contains(flag)) {
            flags.add(flag);
        }
    }

    // Check if player already has flags
    public boolean hasFlag(String flag){
        return flags.contains(flag);
    }

    // Remove flag
    public void removeFlag(String flag){
        flags.remove(flag);
    }

    // Getter
    public List<String> getFlags(){
        return flags;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    // Inventory handling

    // Method to receive / pickup an item
    public void pickUpItem(Item item){
        inventory.addItem(item);
    }

    // Getter for inventory
    public Inventory getInventory(){
        return inventory;
    }

    // Getter for questlog
    public List<Quest> getQuestLog() {
        return questLog;
    }

    // Booleans to check if a player is a specific class or race - needed for specific interactions
    public boolean isWarrior(){
        return characterClass.getCharacterClassName().equals("Warrior");
    }

    public boolean isMage(){
        return characterClass.getCharacterClassName().equals("Mage");
    }

    public boolean isRanger(){
        return characterClass.getCharacterClassName().equals("Ranger");
    }

    public boolean isHuman(){
        return race.getRaceName().equals("Human");
    }

    public boolean isElf(){
        return race.getRaceName().equals("Elf");
    }

    public boolean isDwarf(){
        return race.getRaceName().equals("Dwarf");
    }

    public boolean isOrc(){
        return race.getRaceName().equals("Orc");
    }

}
