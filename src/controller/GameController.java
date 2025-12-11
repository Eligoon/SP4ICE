package controller;
import world.Location;

import util.DataSaving;

public class GameController {
    private Location currentLocation;

    public void initializeGame(){
        Location theClearing = new Location(
                "The Clearing",
                "A glistening flower stands dancing in the gentle breeze in the middle of the clearing. It is taller than you are,\n" +
                        "and strangely glowing below the dew that coats it. This is the sense of danger you felt.\n" +
                        "Roots fly up towards you, attempting to grab and strangle you! "
        );
    Location huntingCabin = new Location(
        "The Hunting Cabin",
            "Through the barricaded window opening of the cabin," +
                    "you can see crates. They may contain something useful. " +
                    "The door is locked. While the building looks abanandoned, " +
                    "the lock is not damaged or old enough that you can just break " +
                    "it. You will need a key to enter."
    );

        // Set starting location to be current location
        currentLocation = theClearing;

        // Connect locations together
        theClearing.addConnectedLocation("north", huntingCabin);
    }

    // move: Handles movement from the players current location to another connected location.
    public void move(String direction) {
        //Look up if there  is a connected lacation in the given direction
        Location newLocation = currentLocation.getConnectedLocation(direction);
        //if the direction does not lead anywhere (null) block movement
        if (newLocation == null) {
            System.out.println("You can't go that way.");
            return; //stop the method here
        }
        //update the current location to the new valid location.
        currentLocation = newLocation;

        // Feedback to the player showing movement and new location name
        System.out.println("You move " + direction + "...");
        System.out.println("You are now at: " + currentLocation.getLocationName());

        //Print the location's description so the player (Description is located in location.java)
        System.out.println(currentLocation.getDescription());
    }

            // Move location





   private DataSaving db = new DataSaving();
   private String url = "jdbc:sqlite:identifier.sqlite";


    // db.connect cannot exist without being in a method, just put it in here for now.
    // Needs to be present in everything containing data, so new game, load game, save game etc.
    public void newGame() {
        db.connect(url);
    }

}
