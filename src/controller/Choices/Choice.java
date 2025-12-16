package controller.Choices;

import controller.GameController;
import creatures.Creature;
import creatures.Player;
import collectibles.Item;
import world.Location;

import java.util.ArrayList;
import java.util.List;

public class Choice {

    private String description;            // Text shown to the player
    private boolean taken = false;         // Checks if this choice has already been taken
    private ChoiceType type;               // Type of choice (MOVE, COMBAT, INTERACT, USEITEM, etc.)

    private Location targetLocation;       // For movement choices
    private Creature enemy;                // For combat choices
    private Creature npc;                  // For NPC interaction choices
    private Item item;                     // For item-related choices (use/equip)
    private String direction;              // Direction for move choices

    private List<Requirement> requirements = new ArrayList<>(); // Requirements for the choice

    private Choice() { }

    // Helper methods to create choices

    public static Choice moveChoice(String description, String direction) {
        Choice c = new Choice();
        c.description = description;
        c.direction = direction;
        c.type = ChoiceType.MOVE;
        return c;
    }

    public static Choice combatChoice(String description, Creature enemy) {
        Choice c = new Choice();
        c.description = description;
        c.enemy = enemy;
        c.type = ChoiceType.COMBAT;
        return c;
    }

    public static Choice interactChoice(String description, Creature npc) {
        Choice c = new Choice();
        c.description = description;
        c.npc = npc;
        c.type = ChoiceType.INTERACT;
        return c;
    }

    public static Choice itemChoice(String description, Item item, ChoiceType type) {
        Choice c = new Choice();
        c.description = description;
        c.item = item;
        c.type = type;
        return c;
    }

    // Requirements

    public void addRequirement(Requirement req) {
        requirements.add(req);
    }

    public boolean isAvailable(Player player) {
        for (Requirement req : requirements) {
            if (!req.isMet(player)) return false;
        }
        return true;
    }

    // Execute choice in GameController
    public void execute(GameController gc) {
        switch (type) {
            case MOVE:
                gc.move(direction);
                break;

            case COMBAT:
                if (enemy != null) gc.handleCombat(enemy);
                break;

            case INTERACT:
                if (npc != null) gc.handleNPCInteraction(npc);
                break;

            case USEITEM:
                break;

            case EQUIPARMOUR:
                gc.handleEquipArmour(item);
                break;

            case EQUIPWEAPON:
                gc.handleEquipWeapon(item);
                break;
        }
        taken = true;
    }

    // Getters and setters (preserved structure)

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

    public Creature getNpc() {
        return npc;
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
