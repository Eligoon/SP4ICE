package util;
import java.sql.*;

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
                     health INTEGER,
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
                    is_hostile BOOLEAN
                );
                """;
            stmt.executeUpdate(sqlNPCState);


            System.out.println("Tables created");

        } catch (SQLException e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }

    /** Explaining comment block of save and load
     *

     */

    // SAVING PART OF THE SAVING OF DATA

    // Saves the overall game state, currently only the player's current location.
    // This allows the game to resume from the same location on load.
        public void saveGameState(GameController gameController) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "REPLACE INTO game_state (id, current_location) VALUES (1, ?)"
            )) {
                stmt.setString(1, gameController.currentLocation.getLocationName());
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
                "REPLACE INTO player (id, name, race, class, health, strength, dexterity, intelligence, location) " +
                        "VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getRace().getRaceName());
            stmt.setString(3, player.getCharacterClass().getClassName());
            stmt.setInt(4, player.getStats().getHealth());
            stmt.setInt(5, player.getStats().getStrength());
            stmt.setInt(6, player.getStats().getDexterity());
            stmt.setInt(7, player.getStats().getIntelligence());
            stmt.setString(8, location.getLocationName());
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
                clear.executeUpdate("DELETE FROM inventory;");
            } catch (SQLException e) {
                System.err.println("ERROR: Failed to clear inventory before saving.");
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
    // False is 0 true is 1 for booleans
    public void saveNPCs(Location location) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "REPLACE INTO npc_state (npc_name, location_name, is_dead, is_hostile) VALUES (?, ?, ?, ?)"))
        {
            for (Creature c : location.getCreatures()) {

                if (c instanceof NPC npc) {
                    stmt.setString(1, npc.getName());
                    stmt.setString(2, location.getLocationName());
                    stmt.setBoolean(3, npc.isDead);
                    stmt.setBoolean(4, npc.isHostile);
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
                 ResultSet rs = stmt.executeQuery("SELECT * FROM player WHERE id = 1"))
            {

                if (rs.next()) {
                    Player player = new Player(
                            rs.getString("name"),
                            rs.getString("race"),
                            rs.getString("class")
                    );

                    player.getStats().setHealth(rs.getInt("health"));
                    player.getStats().setStrength(rs.getInt("strength"));
                    player.getStats().setDexterity(rs.getInt("dexterity"));
                    player.getStats().setIntelligence(rs.getInt("intelligence"));

                    return player;
                }

            } catch (SQLException e) {
                System.err.println("ERROR: Failed to load player.");
                e.printStackTrace();
            }

            return null;
        }


    // LoadInventory //TODO

    // LoadNPCs
    public void loadNPCs(Map<String, Location> allLocations) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM npc_state"))
        {

            while (rs.next())
            {

                String npcName = rs.getString("npc_name");
                String locName = rs.getString("location_name");
                boolean isDead = rs.getBoolean("is_dead");
                boolean isHostile = rs.getBoolean("is_hostile");

                Location location = allLocations.get(locName);
                if (location == null) {
                    System.err.println("WARNING: Location '" + locName
                            + "' not found while loading NPC '"
                            + npcName + "'.");
                    continue;
                }

                boolean npcFound = false;

                for (Creature c : location.getCreatures()) {
                    if (c instanceof NPC npc && npc.getName().equals(npcName)) {
                        npcFound = true;
                        npc.isDead = isDead;
                        npc.isHostile = isHostile;

                        if (npc.isDead) {
                            location.removeCreature(npc);
                        }
                    }
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


    // Delete rows in the tables to prepare for a new game
    public void deleteSave() {
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate("DELETE FROM game_state;");
            stmt.executeUpdate("DELETE FROM player;");
            stmt.executeUpdate("DELETE FROM inventory;");
            stmt.executeUpdate("DELETE FROM npc_state");
            System.out.println("Save deleted.");
        } catch (SQLException e) {
            System.err.println("Could not delete save:");
            e.printStackTrace();
        }
    }
}
