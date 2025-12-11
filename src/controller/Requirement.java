package controller.requirements;

import creatures.Player;

// A requirement is something the player must have or another condition must meet
public interface Requirement {

    // Returns true if the player passes the requirement
    boolean isMet(Player player);

    // Message shown when requirement is not met (Might need this at some point?)
    String getFailureMessage();
}
