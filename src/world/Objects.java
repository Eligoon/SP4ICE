package world;

import util.TextUI;
import creatures.Player;

public class Objects {
    private TextUI ui = new TextUI();
    private boolean murkyWatersTrapTriggered = false;

    /**
     * Checks for trap in "The Murky Waters" location.
     * Deals damage once and shows story messages.
     */
    public void checkForTraps(Location location, Player player, Story story) {
        if (location == null || player == null || story == null) return;

        if (location.getLocationName().equals("The Murky Waters")) {
            if (!murkyWatersTrapTriggered) {
                murkyWatersTrapTriggered = true;

                // Get trap message from story
                String trapMsg = story.getTrapMessage(location.getLocationName());
                ui.displayMsg(trapMsg);

                // Deal fixed damage to player
                int damage = 10;
                player.getStats().takeDamage(damage);
                ui.displayMsg("The trap snaps, dealing " + damage + " damage!");
            } else {
                // Message for revisiting
                String revisitMsg = story.getTrapRevisitMessage(location.getLocationName());
                ui.displayMsg(revisitMsg);
            }
        }
    }
}
