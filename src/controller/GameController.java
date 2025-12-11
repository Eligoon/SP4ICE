package controller;
import util.TextUI;
import world.Location;
import util.DataSaving;
import world.Objects;
import world.Story;

public class GameController {
    // --- Fields / Attributes ---
    //Tracks the players current location
    private Location currentLocation;
    // Text based UI to display messages
    TextUI ui = new TextUI();
    //Story object containing all locations and text for them
    Story emeraldTear = new Story();

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
        //Look up if there  is a connected location in the given direction
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
        object.checkForTrap(currentLocation);
    }


    //--- Getter ---
    public Location getCurrentLocation(){
        return currentLocation;
    }

//--- Database handling --- used for save/load
   private DataSaving db = new DataSaving();
   private String url = "jdbc:sqlite:identifier.sqlite";


    // db.connect cannot exist without being in a method, just put it in here for now.
    // Needs to be present in everything containing data, so new game, load game, save game etc.
    public void newGame() {
        db.connect(url);
    }
}