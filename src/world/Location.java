package world;

import collectibles.Item;
import controller.Choices.Choice;
import creatures.Creature;
import util.TextUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String locationName; // Name of location
    private String description; // Description of location
    private List<Creature> creatures; // Creatures in location
    private List<Item> items; // Items available at location
    private List<Choice> availableChoices; // Choices to make at location
    private Map<String, Location> connectedLocations; // List of locations to go to from here
    private TextUI ui = new TextUI();

    // Full constructor
    public Location(String locationName, String description, List<Creature> creatures, List<Item> items, List<Choice> availableChoices, Map<String, Location> connectedLocations) {
        this.locationName = locationName;
        this.description = description;
        this.creatures = creatures;
        this.items = items;
        this.availableChoices = availableChoices;
        this.connectedLocations = connectedLocations;
    }

    // Constructor for MVP
    public Location(String locationName, String description){
        this.locationName = locationName;
        this.description = description;
        this.creatures = new ArrayList<>();
        this.items = new ArrayList<>();
        this.availableChoices = new ArrayList<>();
        this.connectedLocations = new HashMap<>();
    }

    // Add the connected locations to hashmap
    public void addConnectedLocation(String direction, Location location){
        connectedLocations.put(direction, location);
    }

    // Getter for which locations are connected to the current location
    public Location getConnectedLocation(String direction){
        return connectedLocations.get(direction);
    }

    // Add creature to location
    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    // Add item to location
    public void addItem(Item item){
        items.add(item);
    }

    // Add choices to location
    public void addChoice(Choice choice){
        availableChoices.add(choice);
    }

    // Check if the locations has creatures here
    public boolean hasCreatures(){
        return !creatures.isEmpty();
    }

    // Check if there are items at this location
    public boolean hasItems(){
        return !items.isEmpty();
    }

    public String getLocationName() {
        return locationName;
    }

    public String getDescription() {
        return description;
    }

    // Shows creatures, items and which directions to go
    public void displayLocation(){
        if (hasCreatures()){
            ui.displayMsg("Creatures here:");
            for (Creature creature : creatures){
                ui.displayMsg(creature.getName());
            }
        }

        if (hasItems()){
            ui.displayMsg("Items here:");
            for (Item item : items){
                ui.displayMsg(item.getItemName());
            }
        }

        if (!connectedLocations.isEmpty()){
            ui.displayMsg("You can go: ");
            for (String direction : connectedLocations.keySet()){
                ui.displayMsg(direction);
            }
        }
    }
}
