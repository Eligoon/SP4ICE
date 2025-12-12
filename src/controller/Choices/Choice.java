package controller.Choices;

import creatures.Creature;
import creatures.Player;
import world.Location;
import controller.GameController;
import java.util.ArrayList;
import java.util.List;

public class Choice {

    private String description;        // Text shown to the player

    private boolean taken = false;     // Boolean to check if an option has already been taken

    private ChoiceType type;           // What the choice does (move, combat, etc.) (Enum)

    private Location targetLocation;   // For MOVE choices
    private Creature enemy;            // For COMBAT choices

    private List<Requirement> requirements = new ArrayList<>();
    // Requirements: item, npc dead, race, class

    // Constructors below for choices

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

    // Requirement for choices

    // Add a requirement (item, race, class, npc dead, etc.)
    public void addRequirement(Requirement req) {
        requirements.add(req);
    }

    // Check if ALL requirements for this choice are met
    public boolean isAvailable(Player player) {
        for (Requirement req : requirements) {
            if (!req.isMet(player)) {
                return false;  // A requirement failed means the choice is hidden
            }
        }
        return true;
    }

    // Execution to gamecontroller

    // Tell GameController what to do when this choice is picked
    public void execute(GameController gc) {

        switch (type) {

            case MOVE:
                // Move player to another location
                gc.changeLocation(targetLocation);
                break;

            case COMBAT:
                // Start a fight
                gc.handleCombat(enemy);
                break;

            case INTERACT:
            case USEOBJECT:

        }
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
