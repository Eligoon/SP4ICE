package controller.Choices;

import creatures.Player;

public class RaceRequirement implements Requirement {

    private String requiredRace;

    public RaceRequirement(String requiredRace) {
        this.requiredRace = requiredRace;
    }

    @Override
    public boolean isMet(Player player) {
        return player.getRace().getRaceName().equalsIgnoreCase(requiredRace);
    }
}
