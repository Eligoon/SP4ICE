package controller;

import collectibles.Armor;
import collectibles.Health;
import controller.Choices.Choice;
import creatures.Creature;
import creatures.NPC;
import util.TextUI;
import world.Location;
import util.DataSaving;
import world.Objects;
import world.Story;
import collectibles.Item;

import java.util.List;

public class GameController {
    // --- Fields / Attributes ---
    //Tracks the players current location
    private Location currentLocation;
    // Text based UI to display messages
    TextUI ui = new TextUI();
    //Story object containing all locations and text for them
    Story emeraldTear = new Story();

    //--- Database handling --- used for save/load
    private static DataSaving db = new DataSaving();
    private static String url = "jdbc:sqlite:identifier.sqlite";

    // Static initializer for database connection
    // Runs once when the class is loaded
    static {
        try {
            db.connect(url);
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ---- Initializing the game ---
    public void initializeGame() {
        // Displays the welcome message
        emeraldTear.displayWelcomeMessage();

        // Loads story, with locations and connects all locations together
        emeraldTear.loadStory();

        // Set starting location to be current location
        currentLocation = emeraldTear.getLocation("The Clearing");
    }

    // --- Movement logic ---
    // move: Handles movement from the players current location to another connected location.
    public void move(String direction) {
        //Look up if there is a connected location in the given direction
        Location newLocation = currentLocation.getConnectedLocation(direction);
        //if the direction does not lead anywhere (null) block movement
        if (newLocation == null) {
            ui.displayMsg("You can't go that way.");
            return; //stop the method here
        }
        //update the current location to the new valid location.
        currentLocation = newLocation;

        // Feedback to the player showing movement and new location name
        ui.displayMsg("You move " + direction + "...");
        ui.displayMsg("You are now at: " + currentLocation.getLocationName());

        //Print the location's description so the player (Description is located in location.java)
        ui.displayMsg(currentLocation.getDescription());
        // After movement, check for trap
        Objects object = new Objects();
        object.checkForTraps(currentLocation);
    }

    public void handleCombat(Creature enemy) {
        if (enemy == null) return;

        ui.displayMsg("You engage in combat with " + enemy.getName() + "!");

        boolean playerTurn = true;

        // Combat continues until either the player or the enemy is dead
        while (!enemy.isDead() && !player.isDead()) {
            if (playerTurn) {
                ui.displayCombatStatus(player, enemy);

                int choice = -1;
                // Force valid input
                while (choice != 1 && choice != 2) {
                    ui.displayMsg("Choose your action:");
                    ui.displayMsg("1. Attack");
                    ui.displayMsg("2. Use Item");
                    choice = ui.promptNumeric("Enter choice number:");
                }

                if (choice == 1) {
                    int playerDamage = player.attack(enemy); // scales with class + weapon
                    ui.displayMsg(player.getName() + " attacks " + enemy.getName() + " for " + playerDamage + " damage!");

                    if (enemy.getCurrentHP() <= 0) {
                        enemy.setDead(true);
                        ui.displayMsg(enemy.getName() + " has been defeated!");
                        break; // exit combat loop
                    }
                } else if (choice == 2) {
                    // Placeholder: implement item usage logic
                    ui.displayMsg("You used an item!");
                }

            } else {
                // Enemy turn
                if (enemy instanceof NPC) {
                    NPC npc = (NPC) enemy;
                    npc.CPU_Attack(player); // automatic attack

                    if (player.getCurrentHP() <= 0) {
                        player.setDead(true);
                        ui.displayMsg(player.getName() + " has been defeated by " + npc.getName() + "!");
                        break; // exit combat loop
                    }

                    if (npc.getCurrentHP() <= 0) {
                        npc.setDead(true);
                        ui.displayMsg(npc.getName() + " has been defeated!");
                        break; // exit combat loop
                    }
                }
            }

            // Switch turns
            playerTurn = !playerTurn;
        }

        ui.displayMsg("Combat has ended.");
    }



    public void handleNPCInteraction(Creature npc) {
        ui.displayMsg("You interact with " + npc.getName());
        // dialogue, quest logic, etc.
    }

    public void handleUseItem(Item item) {
        ui.displayMsg("You use this item: " + Health.getItemName);
        // Using health item logic
    }

    public void handleEquipArmour(Item armour) {
        ui.displayMsg("You equip this piece of armor: " + Armor.getItemName);
        // Equipping armor logic
    }

    public void handleEquipWeapon(Item weapon) {
        ui.displayMsg("You equip this weapon: " + Weapon.getItemName);
        // Equipping weapon logic
    }


    //--- Getters ---
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public List<Choice> getLocationChoices() {
        return currentLocation.getAvailableChoices();
    }

    /* Example of how choice could be processed not full logic
    List<Choice> available = textUI.getAvailableChoices(allChoices, player);

    Choice picked = textUI.promptChoice(available, "What do you want to do?");

    picked.execute(this);   // <- triggers MOVE, COMBAT, etc.

    // Mark it as taken so it turns gray next time
    picked.setTaken(true);
     */
}
