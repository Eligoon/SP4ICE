package controller;

import collectibles.Armor;
import collectibles.Health;
import controller.Choices.Choice;
import creatures.Creature;
import creatures.NPC;
import creatures.Player;
import creatures.attributes.Inventory;
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
    // Player object (this is you!)
    private Player player;

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

        // TODO Show character specific intro, might need a characterCreator helper class
    }

    // Method to show class specific intros
    private void displayClassSpecificIntro(){
        if(player.isWarrior()){
            ui.displayMsg("Since the times of renewal, when life became more than suffering, your family line has protected a small\n" +
                    "village near the forests that guards the mountain and the eight great trees. The villagers know the\n" +
                    "importance of the surrounding lands, and their dangers, respecting both.\n" +
                    "But something has been astray recently, the wildlife and usual threats roaming nearby has become feral. A\n" +
                    "few villagers have gone missing, including your little sister. You fear the worst, and thus you have sworn an\n" +
                    "oath to your mother, that you will find the missing villagers, and find the reason for the upsetting of the\n" +
                    "balance in your home region");
        } else if (player.isMage()){
            ui.displayMsg("Your life has never been normal. Magic flow through your veins and knowledge your mind. You are the peak\n" +
                    "of sorcery at the tower of mages, the school for those gifted, such as yourself. But lately you have noticed\n" +
                    "the elders, your teachers and mentors, have been scared, whispering together in the corridors of the towers\n" +
                    "spiralling ascend.\n" +
                    "Someone has felt a rift in the veil of magic, emanating from the mountain of origins, the likely source, being\n" +
                    "one of the eight great trees. You are too young to be sent out on your own, yet the elders are too scared to\n" +
                    "go themselves. But when have you ever followed orders from your elders anyways? You set out, to restore\n" +
                    "the balance and prove yourself.\n");
        } else if (player.isRanger()){
            ui.displayMsg("Born near the swamplands that surround the eastern part of the mountain of origin, life was harsh but fair.\n" +
                    "Food was plenty, if you knew what to eat and what would eat you. The air was humid and the land moist\n" +
                    "and soggy. But it was home, and it was better than the scorching torment that the collective memory of\n" +
                    "your people held from ages past.\n" +
                    "Here to survive, you have to be fast and good with the weapons given to you from since you could walk.\n" +
                    "Here you have to honour nature and be one with it, or it would swallow you whole. Yet as of late, the\n" +
                    "swamp had not been whole, the waters were slowly drying up, the life that was always abundant, was not\n" +
                    "dwindling. You will not stand for such, not if you have a say in it. Hence why you venture out, to find the\n" +
                    "cause.\n");
        }


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
                } else {
                    Inventory.useItem(player);
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
        ui.displayMsg("You use this item: " + item.getItemName());
        // Using item logic
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
