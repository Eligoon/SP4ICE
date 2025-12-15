package creatures;

import collectibles.Armor;
import collectibles.Item;
import collectibles.Quest;
import collectibles.Weapon;
import creatures.attributes.CharacterClass;
import creatures.attributes.Class;
import creatures.attributes.Inventory;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import util.TextUI;
import world.Location;

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

        // Apply race and class bonuses
        race.applyRacialBonuses(this.stats);
        characterClass.applyCharacterClassBonuses(this.stats);

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
        ui.displayMsg("New quest acquired: " + quest.getQuestName());
        // TODO Add getter in quest?
    }

    // Method to complete quest for the player
    public void completeQuest(String questID){ // Marks quest as completed
        for (Quest quest : questLog) {
            if (quest.getQuestID.equals(questID)) {
                completeQuest(this);
                ui.displayMsg("The quest: " + quest.getQuestName() + "has been completed!");
                return;
            }
        }
    }

    // Method to receive / pickup an item
    public void pickUpItem(Item item){
        Inventory.addItem(item);
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
