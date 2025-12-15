package controller;

import collectibles.Armor;
import collectibles.Health;
import collectibles.Weapon;
import controller.Choices.Choice;
import creatures.Creature;
import creatures.NPC;
import creatures.Player;
import creatures.attributes.CharacterClass;
import creatures.attributes.Inventory;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import util.TextUI;
import world.Location;
import util.DataSaving;
import world.Objects;
import world.Story;
import collectibles.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void handleGameStart() {
        boolean hasSave = db.checkForSave();

        if (hasSave) {
            ui.displayMsg("A saved game was found.");
            ui.displayMsg("Do you want to load the saved game or start a new game?");
            ui.displayMsg("1. Load saved game");
            ui.displayMsg("2. Start a new game (overwrite old save)");

            int option = -1;
            while (option != 1 && option != 2) {
                option = ui.promptNumeric("Enter choice number:");
            }

            if (option == 1) {
                loadGame();  // load existing save
                return;
            } else {
                ui.displayMsg("Starting a new game...");
                db.deleteSave();  // clear old save
            }
        } else {
            ui.displayMsg("No saved game found. Starting a new game...");
        }

        // Initialize new game
        initializeGame();
    }

    private void loadGame() {
        // 1. Load the last saved location
        String savedLocationName = db.loadGameState();

        // 2. Load all story locations
        emeraldTear.loadStory();
        Map<String, Location> allLocations = emeraldTear.getLocationsMap();

        // 3. Load player
        player = db.loadPlayer();

        // 4. Set current location
        currentLocation = db.loadLocation(savedLocationName, allLocations);

        // 5. Load inventory
        db.loadInventory(player);

        // 6. Load NPC states
        db.loadNPCs(allLocations, savedLocationName);

        ui.displayMsg("Game loaded successfully!");
        ui.displayMsg("You are at: " + currentLocation.getLocationName());
        ui.displayMsg(currentLocation.getDescription());
    }



    public void createPlayer() {
        // --- Player Name ---
        String name = ui.promptText("Enter your character's name:");

        // --- Choose Race ---
        List<Race> races = Race.getAllRaces();
        ui.displayMsg("Choose your race:");
        for (int i = 0; i < races.size(); i++) {
            Race r = races.get(i);
            ui.displayMsg((i + 1) + ". " + r.getRaceName() + " - " + r.getSpecialAbility());
        }

        int raceChoice = -1;
        while (raceChoice < 1 || raceChoice > races.size()) {
            raceChoice = ui.promptNumeric("Enter choice number:");
        }
        Race chosenRace = races.get(raceChoice - 1);

        // --- Choose Class ---
        List<CharacterClass> classes = CharacterClass.getAllCharacterClasses();
        ui.displayMsg("Choose your class:");
        for (int i = 0; i < classes.size(); i++) {
            CharacterClass c = classes.get(i);
            ui.displayMsg((i + 1) + ". " + c.getCharacterClassName() + " - Skills: " + String.join(", ", c.getSpecialSkills()));
        }

        int classChoice = -1;
        while (classChoice < 1 || classChoice > classes.size()) {
            classChoice = ui.promptNumeric("Enter choice number:");
        }
        CharacterClass chosenClass = classes.get(classChoice - 1);

        // --- Base Stats ---
        Stats stats = new Stats(100, 10, 10, 10); // Default: HP, STR, DEX, INT

        // Apply racial and class bonuses
        chosenRace.applyRacialBonuses(stats);
        chosenClass.applyCharacterClassBonuses(stats);

        // --- Create Player ---
        this.player = new Player(name, chosenRace, chosenClass, stats);

        // --- Display class-specific intro ---
        displayClassSpecificIntro();
    }


    // ---- Initializing the game ---
    public void initializeGame() {
        // 1. Display welcome message
        emeraldTear.displayWelcomeMessage();

        // 2. Load story, including all locations and connections
        emeraldTear.loadStory();

        // 3. Create the player character
        createPlayer();

        // 4. Display class-specific intro immediately after player creation
        displayClassSpecificIntro();

        // 5. Set starting location
        currentLocation = emeraldTear.getLocation("The Clearing");
        ui.displayMsg(emeraldTear.getLocationDescription(currentLocation));
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

        ui.displayMsg("You engage in combat with the " + enemy.getName() + "!");

        boolean playerTurn = true;

        // Combat continues until either the player or the enemy is dead
        while (!enemy.getIsDead() && !player.getIsDead()) {
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
                    List<Item> usableItems = player.getInventory().getUsableItems();
                    if (usableItems.isEmpty()) {
                        ui.displayMsg("No usable items in your inventory!");
                    } else {
                        // Let player choose which item to use
                        Item itemToUse = ui.promptChoiceOb(usableItems, "Choose an item to use:");
                        handleUseItem(itemToUse);
                    }
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


    public void handleNPCInteraction(Creature creature) {
        if (!(creature instanceof NPC)) return;

        NPC npc = (NPC) creature;

        // Get list of choices from Story
        List<Choice> dialogueOptions = emeraldTear.getDialogueChoices(npc, player);
        if (dialogueOptions.isEmpty()) {
            ui.displayMsg(npc.getName() + " has nothing to say.");
            return;
        }

        // Prompt the player to choose a dialogue
        Choice selected = ui.promptChoiceOb(dialogueOptions, "Choose your dialogue:");

        // Handle the chosen dialogue directly
        emeraldTear.handleDialogue(npc, player, selected);

        // Trigger combat if NPC is hostile
        if (npc.isHostile() && !npc.isDead()) {
            ui.displayMsg(npc.getName() + " attacks!");
            handleCombat(npc);
        }
    }






    public void handleUseItem(Item item) {
        if (item == null) {
            ui.displayMsg("No item selected.");
            return;
        }

        ui.displayMsg("You use the item: " + item.getItemName());
        item.use(player); // Calls the item’s use() logic
        player.getInventory().removeItem(item); // Remove from inventory if consumable
    }


    public void handleEquipArmour(Item armourItem) {
        if (!(armourItem instanceof Armor)) {
            ui.displayMsg("This item is not armor and cannot be equipped.");
            return;
        }

        Armor armor = (Armor) armourItem;
        player.getInventory().equipArmor(armor);
        ui.displayMsg("You equipped: " + armor.getItemName());
    }

    public void handleEquipWeapon(Item weaponItem) {
        if (!(weaponItem instanceof Weapon)) {
            ui.displayMsg("This item is not a weapon and cannot be equipped.");
            return;
        }

        Weapon weapon = (Weapon) weaponItem;
        player.getInventory().equipWeapon(weapon);
        ui.displayMsg("You equipped: " + weapon.getItemName());
    }



    //--- Getters ---
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public List<Choice> getLocationChoices() {
        return currentLocation.getAvailableChoices();
    }

    public Player getPlayer() {
        return player;
    }

    public TextUI getUi() {
        return ui;
    }
}
