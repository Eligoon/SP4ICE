package controller.Choices;

import creatures.Player;

public class ItemRequirement implements Requirement {

    private String itemName; // The item the player must have

    public ItemRequirement(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean isMet(Player player) {
        return player.getInventory().hasItem(itemName);
    }
}
