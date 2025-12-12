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
    // Player chooses a class
    private String characterClass;
    // The player's inventory holding items
    private Inventory inventory;
    // A list of quests the player has accepted
    private List<Quest> questLog;
    // The currently equipped weapon
    private Weapon equippedWeapon;

    // --- Constructor for new player object ---
    public Player(String name, Race race, String characterClass, Stats stats) {
        super(name, race, stats);

        this.characterClass = characterClass;

        // create empty inventory for the player
        this.inventory = new Inventory();
        // create empty quest log
        this.questLog = new ArrayList<>();
    }

    @Override
    public void interact() { // Interaction placeholder
        //TODO
    }

    public void addQuest(Quest quest){ // add a quest to the player's quest log
        questLog.add(quest);
    }

    public void completeQuest(String questID){ // Marks quest as completed
        //TODO
    }

    // Combat methods

    // Player attacks a target
    public int attack(Creature target) {
        int baseDamage = 0;

        // Damage scales with class + equipped weapon
        switch (characterClass.toLowerCase()) {
            case "warrior":
                baseDamage = stats.getStrength();
                break;
            case "ranger":
                baseDamage = stats.getDexterity();
                break;
            case "mage":
                baseDamage = stats.getIntelligence();
                break;
        }

        // Add weapon damage if equipped
        if (equippedWeapon != null) {
            baseDamage += equippedWeapon.getDamage();
        }

        // Apply damage to the target
        target.takeDamage(baseDamage);

        return baseDamage;
    }

    // Player can heal a fixed amount
    public void heal(int amount) {
        int newHP = stats.getCurrentHealth() + amount;
        stats.setCurrentHealth(Math.min(newHP, stats.getMaxHealth())); // cannot exceed max HP
    }

    // --- Getters ---
    public String getCharacterClass() {
        return characterClass;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Quest> getQuestLog() {
        return questLog;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
