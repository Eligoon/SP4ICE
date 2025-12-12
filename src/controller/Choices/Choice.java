package controller.Choices;

import controller.GameController;
import creatures.Creature;
import creatures.Player;
import collectibles.Item;
import world.Location;
import world.Objects;

import java.util.ArrayList;
import java.util.List;

public class Choice {

    private String description;        // Text shown to the player
    private boolean taken = false;     // Boolean to check if an option has already been taken
    private ChoiceType type;           // What the choice does (move, combat, etc.)

    private Location targetLocation;   // For MOVE choices
    private Creature enemy;            // For COMBAT choices
    private Objects object;            // For INTERACT choices
    private Item item;                 // For USEITEM / EQUIPARMOUR / EQUIPWEAPON choices

    private List<Requirement> requirements = new ArrayList<>();
    // Requirements: item, npc dead, race, class


    // Constructors

    // Movement choice
    public Choice(String description, Location targetLocation) {
        this.description = description;
        this.targetLocation = targetLocation;
        this.type = ChoiceType.MOVE;
    }

    // Combat choice
    public Choice(String description, Creature enemy) {
        this.description = description;
        this.enemy = enemy;
        this.type = ChoiceType.COMBAT;
    }

    // Interact choice
    public Choice(String description, Objects object) {
        this.description = description;
        this.object = object;
        this.type = ChoiceType.INTERACT;
    }

    // Item-based choices (use / equip)
    public Choice(String description, Item item, ChoiceType type) {
        this.description = description;
        this.item = item;
        this.type = type;
    }


    // Requirement handling

    public void addRequirement(Requirement req) {
        requirements.add(req);
    }

    public boolean isAvailable(Player player) {
        for (Requirement req : requirements) {
            if (!req.isMet(player)) {
                return false;
            }
        }
        return true;
    }


    // Execute the choice to gamecontroller

    public void execute(GameController gc) {

        switch (type) {

            case MOVE:
                gc.changeLocation(targetLocation);
                break;

            case COMBAT:
                gc.handleCombat(enemy);
                break;

            case INTERACT:
                gc.handleInteraction(object);
                break;

            case USEITEM:
                gc.handleUseItem(item);
                break;

            case EQUIPARMOUR:
                gc.handleEquipArmour(item);
                break;

            case EQUIPWEAPON:
                gc.handleEquipWeapon(item);
                break;
        }

        taken = true; // Mark choice as taken
    }


    // Getters and setters

    public String getDescription() {
        return description;
    }

    public ChoiceType getType() {
        return type;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public Creature getEnemy() {
        return enemy;
    }

    public Objects getObject() {
        return object;
    }

    public Item getItem() {
        return item;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
