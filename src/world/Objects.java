package world;

import util.TextUI;
/*import world.Location;*/

public class Objects {
    TextUI ui = new TextUI();
    // Trap only happens once
    private boolean trapTriggered = false;

    //Check if current location should trigger the trap
    public void checkForTraps(Location location) {
        //Trap only exists in The Murky Waters
        if (location.getLocationName().equals("The Murky Waters")) {
            if (!trapTriggered) {
                // First time triggers trap and deals damage
                ui.displayMsg("You go around the bubbles, those are surely deadly right?" +
                        "Well, someone thought about that and placed a spiked trap below the waters.");
                ui.displayMsg("It snaps around your leg, but you still manage to move forward.");
                //TODO: Add damage to the player here
                trapTriggered = true; // so it only triggers once
            } else {
                //After first time, no more damage and different message
                ui.displayMsg("You return back through the murky waters, " +
                        "this time it feels a little easier. Though the sensation of " +
                        "moving through here is still nasty.");
            }
        }
    }
}
    /* FIRST ATTEMPT BUT ENDED UP WITH SOLUTION ABOVE
//Check if the current location has a trap and activates it
    public void checkForTrap(GameController controller){
        //Get the players current location
        Location location = controller.getCurrentLocation();

        //if the locations name matches the trap room
        if (location.getLocationName().equals("The Murky Waters Trap")){
            activateTrap();
        }
    }
    //activate trap
    private void activateTrap(){
        ui.displayMsg("You go around the bubbles, those are surely deadly right? Well, someone thought about that and \n" +
                "placed a spiked trap below the waters. It snaps around your leg, but you still manage to move \n" +
                "forward.");
        //TODO: Bør teksten ikke være på story? fix dette.
        //TODO: Add damage to the player
    }

}
*/