package controller.Choices;

import creatures.Player;

public class ClassRequirement implements Requirement {

    private String requiredClass;

    public ClassRequirement(String requiredClass) {
        this.requiredClass = requiredClass;
    }

    @Override
    public boolean isMet(Player player) {
        return player.getCharacterClass().getClassName().equalsIgnoreCase(requiredClass);
    }
}
