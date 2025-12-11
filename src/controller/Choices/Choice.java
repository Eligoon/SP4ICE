package controller.Choices;

import creatures.Creature;
import creatures.Player;
import world.Location;

import java.util.ArrayList;
import java.util.List;

public class Choice {

    private String description;        // Text shown to the player

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

}
