package controller;
import world.Location;

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




}
