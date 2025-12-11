package controller.Choices;

import creatures.Player;

// A requirement is something the player must have or another condition must meet
public interface Requirement {

    // Returns true if the player passes the requirement
    boolean isMet(Player player);

}
