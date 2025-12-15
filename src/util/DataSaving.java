package util;
import java.sql.*;
import java.util.Map;

import collectibles.Item;
import controller.GameController;
import creatures.attributes.Race;
import creatures.attributes.Stats;
import world.Location;
import creatures.Player;
import creatures.NPC;
import creatures.Creature;
import creatures.attributes.CharacterClass;

// TRUNCATE is not supported in SQLite

public class DataSaving {
    // Connection to database
    private Connection connection;

    // Method to establish connection to our database
    public void connect(String url) {
        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Creating tables for gameState, player, inventory and NPC if they do not already exist.
    // Should only run the very first time the game is run.
    private void createTables() {
        try (Statement stmt = connection.createStatement()) {

            String sqlGameState = """
                CREATE TABLE IF NOT EXISTS game_state (
                    id INTEGER PRIMARY KEY,
                    current_location VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlGameState);

            String sqlPlayer = """
                 CREATE TABLE IF NOT EXISTS player (
                      id INTEGER PRIMARY KEY,
                      name VARCHAR(100),
                      race VARCHAR(50),
                      class VARCHAR(50),
                      max_health INTEGER,
                      current_health INTEGER,
                      strength INTEGER,
                      dexterity INTEGER,
                      intelligence INTEGER,
                      location VARCHAR(100)
                 );
                 """;
            stmt.executeUpdate(sqlPlayer);

            String sqlInventory = """
                CREATE TABLE IF NOT EXISTS inventory (
                    player_id INTEGER,
                    item_name VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlInventory);

            String sqlNPCState = """
                CREATE TABLE IF NOT EXISTS npc_state (
                    npc_name VARCHAR(100) PRIMARY KEY,
                    location_name VARCHAR(100),
                    is_dead BOOLEAN,
                    is_hostile BOOLEAN,
                    item_name VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlNPCState);


            System.out.println("Tables created");

        } catch (SQLException e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }


    /**
     * Game Save/Load
     *
     * This class handles saving and loading the game state, player information,
     * inventory, and NPC states to and from a database. It provides methods to
     * persist the game's progress and resume it later.
     *
     * SAVE SEQUENCE:
     * 1. savePlayer(player, location)
     *      - Save the full player data, including stats and current location.
     * 2. saveInventory(player)
     *      - Save all items in the player's inventory.
     * 3. saveNPCs(location)
     *      - Save the state of all NPCs in the current location.
     * 4. saveGameState(gameController)
     *      - Save the overall game state (currently only location).
     * 5. saveCurrentLocation(location)
     *      - Update the location independently when only location changes.
     *
     * LOAD SEQUENCE:
     * 1. loadGameState()
     *      - Get the last saved location of the player.
     * 2. loadLocation(locationName, allLocations)
     *      - Convert the location name into a Location object.
     * 3. loadPlayer()
     *      - Load the player's stats and information.
     * 4. loadInventory(player)
     *      - Load all items into the player's inventory.
     * 5. loadNPCs(allLocations)
     *      - Load NPCs into all locations, updating their status (dead/hostile).
     *
     * OTHER FUNCTIONALITY:
     * - checkForSave()
     *      - checks if there is a previously saved game.
     * - deleteSave()
     *      - Clears all saved data from the database tables.
     *      - Prepares the game for a fresh start.
     *
     * NOTES:
     * - Prepared statements are used to prevent SQL injection. (SQL injection is an attack targeting the database of a program by inserting/injecting SQL code into an SQL call. The attack exploits a vulnerability in the handling of user input and database calls.)
     * - Boolean values are stored as 0 (false) and 1 (true) in the database.
     * - Inventory saving uses batch inserts for efficiency.
     * - Loading NPCs assumes all NPCs are preloaded in their respective locations.
     * - ItemRegistry is used to create new item instances when loading inventory.
     */


    // SAVING PART OF THE SAVING OF DATA

    // Saves the overall game state, currently only the player's current location.
    // This allows the game to resume from the same location on load.
        public void saveGameState(GameController gameController) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "REPLACE INTO game_state (id, current_location) VALUES (1, ?)"
            )) {
                stmt.setString(1, gameController.getCurrentLocation().getLocationName());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("ERROR: Failed to save game state.");
                System.err.println("Reason: " + e.getMessage());
                e.printStackTrace();
            }
        }


    // the ? will be replaced once we have a concrete location by the getter methods (part of preparedstatement)
    // Saves only the player's current location.
    // Useful when the location changes without saving the full game?
        public void saveCurrentLocation(Location location) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "REPLACE INTO game_state (id, current_location) VALUES (1, ?)"))
            {
                stmt.setString(1, location.getLocationName());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("ERROR: Failed to save current location.");
                System.err.println("Reason: " + e.getMessage());
                e.printStackTrace();
            }
        }


    // the ? will be replaced once we have a concrete player by the getter methods (part of preparedstatement)
    // Saves the player's information including:
    // - Name
    // - Race and Class
    // - Stats (health, strength, dexterity, intelligence)
    // - Current location
    public void savePlayer(Player player, Location location) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "REPLACE INTO player (id, name, race, class, max_health, current_health, strength, dexterity, intelligence, location) " +
                        "VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getRace().getRaceName());
            stmt.setString(3, player.getCharacterClass().getCharacterClassName());

            stmt.setInt(4, player.getStats().getMaxHealth());
            stmt.setInt(5, player.getStats().getCurrentHealth());
            stmt.setInt(6, player.getStats().getStrength());
            stmt.setInt(7, player.getStats().getDexterity());
            stmt.setInt(8, player.getStats().getIntelligence());
            stmt.setString(9, location.getLocationName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save player.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // the ? will be replaced once we have a concrete inventory by the getter methods (part of preparedstatement)
    // Saves all items currently in the player's inventory.
    // Each item is stored in a separate row.
    // Before saving, the table is cleared to avoid duplicates.
    public void saveInventory(Player player) {
        try (Statement clear = connection.createStatement()) {
            clear.executeUpdate("DELETE FROM inventory WHERE player_id = 1;");
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to clear inventory before saving.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }

        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO inventory (player_id, item_name) VALUES (1, ?)"))
        {
            for (Item item : player.getInventory().getItems()) {
                stmt.setString(1, item.getItemName());
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save inventory.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // the ? will be replaced once we have a concrete NPCs by the getter methods (part of preparedstatement)
    // Booleans:  False --> 0 , true --> 1
    public void saveNPCs(Location location) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "REPLACE INTO npc_state (npc_name, location_name, is_dead, is_hostile) VALUES (?, ?, ?, ?)"))
        {
            for (Creature c : location.getCreature()) {

                if (c instanceof NPC npc) {
                    stmt.setString(1, npc.getName());
                    stmt.setString(2, location.getLocationName());
                    stmt.setBoolean(3, npc.isDead());
                    stmt.setBoolean(4, npc.isHostile());
                    stmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save NPCs for location: " + location.getLocationName());
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // LOADING PART OF THE SAVING OF DATA

    // Loads the saved game state.
    // Returns the name of the player's last saved location.
        public String loadGameState() {
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT current_location FROM game_state WHERE id = 1"))
            {
                if (rs.next())
                {
                    return rs.getString("current_location");
                }
            } catch (SQLException e) {
                System.err.println("ERROR: Failed to load game state.");
                System.err.println("Reason: " + e.getMessage());
                e.printStackTrace();
            }

            return null; // No save found
        }


    // Loads a location object by name from a map of all locations.
    // Returns null if the location is not found.
        public Location loadLocation(String locationName, Map<String, Location> allLocations) {
            Location loc = allLocations.get(locationName);

            if (loc == null) {
                System.err.println("ERROR: Location " + locationName + " not found during load.");
            }

            return loc;
        }


    // Loads the player's basic information and stats from the database.
    // Returns a Player object, or null if no saved player exists.
    public Player loadPlayer() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, race, class, max_health, current_health, strength, intelligence, dexterity, location FROM player WHERE id = 1"))
        {
            if (rs.next()) {

                Race race = getRaceFromString(rs.getString("race"));
                CharacterClass cls = getClassFromString(rs.getString("class"));

                Stats stats = new Stats(
                        rs.getInt("max_health"),
                        rs.getInt("strength"),
                        rs.getInt("dexterity"),
                        rs.getInt("intelligence")
                );

                stats.setCurrentHealth(rs.getInt("current_health"));

                return new Player(
                        rs.getString("name"),
                        race,
                        cls,
                        stats
                );
            }

        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load player.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    // Helper method to convert string to Race object
    public Race getRaceFromString(String raceName) {
        return switch (raceName.toLowerCase()) {
            case "human" -> Race.createHuman();
            case "dwarf" -> Race.createDwarf();
            case "elf" -> Race.createElf();
            case "orc" -> Race.createOrc();
            default -> Race.createHuman(); // fallback
        };
    }

    // Helper method to convert string to CharacterClass object
    public CharacterClass getClassFromString(String className) {
        return switch (className.toLowerCase()) {
            case "warrior" -> CharacterClass.createWarrior();
            case "mage"    -> CharacterClass.createMage();
            case "ranger"  -> CharacterClass.createRanger();
            default -> CharacterClass.createWarrior(); // fallback
        };
    }


    // Loads all items in the player's inventory from the database
    // Uses the ItemRegistry to create item instances by name
    public void loadInventory(Player player) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT item_name FROM inventory WHERE player_id = 1"))
        {
            // clear existing inventory
            player.getInventory().clearInventory();

            while (rs.next()) {
                String itemName = rs.getString("item_name");

                Item item = ItemRegistry.create(itemName);

                if (item != null) {
                    player.getInventory().addItem(item);
                } else {
                    System.err.println("WARNING: Item '" + itemName + "' not found in ItemRegistry.");
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load inventory for player.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // LoadNPCs
    public void loadNPCs(Map<String, Location> allLocations, String savedLocation) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT npc_name, location_name, is_dead, is_hostile, item_name FROM npc_state"))
        {

            while (rs.next())
            {
                String npcName = rs.getString("npc_name");
                String locName = rs.getString("location_name");
                boolean isDead = rs.getBoolean("is_dead");
                boolean isHostile = rs.getBoolean("is_hostile");

                Location location = allLocations.get(locName);
                if (location == null) {
                    System.err.println("WARNING: Location " + locName
                            + " not found while loading NPC "
                            + npcName + ".");
                    continue;
                }

                boolean npcFound = false;
                NPC npcToRemove = null;

                for (Creature c : location.getCreature()) {
                    if (c instanceof NPC npc && npc.getName().equals(npcName)) {
                        npcFound = true;

                        npc.setDead(isDead);
                        npc.setHostile(isHostile);

                        // --- Removal conditions ---
                        if (npc.isDead()) {
                            npcToRemove = npc;
                        }
                        else if (npc.doesDespawnWhenLeaving() &&
                                !savedLocation.equals(npc.getOriginalLocation())) {
                            npcToRemove = npc;
                        }
                    }
                }

                // Remove AFTER loop to avoid concurrent modification
                if (npcToRemove != null) {
                    location.removeCreature(npcToRemove);
                }

                if (!npcFound) {
                    System.err.println("WARNING: NPC " + npcName
                            + " not found in location " + locName
                            + " during load.");
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load NPC states from database.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Checks the database for a previously saved game
    public boolean checkForSave() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS save_count FROM game_state")) {
            if (rs.next()) {
                return rs.getInt("save_count") > 0;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to check for save.");
            e.printStackTrace();
        }
        return false;
    }

    // Delete rows in the tables to prepare for a new game
    public void deleteSave() {
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate("DELETE FROM game_state;");
            stmt.executeUpdate("DELETE FROM player;");
            stmt.executeUpdate("DELETE FROM inventory;");
            stmt.executeUpdate("DELETE FROM npc_state;");
            System.out.println("Deleted earlier game.");
        } catch (SQLException e) {
            System.err.println("Could not delete earlier game.");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
