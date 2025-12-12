package creatures;

import collectibles.Item;
import creatures.attributes.Stats;
import util.TextUI;
import java.util.List;

public class NPC extends Creature {
    private List<String> dialogue;  // Lines the NPC can say
    private Object questToGive;     // Placeholder for a Quest class
    private Item itemHeld;          // Item the NPC is holding
    private boolean isHostile;      // Determines if NPC will attack player
    private boolean isDead;         // Tracks if NPC is dead

    private TextUI ui = new TextUI(); // TextUI instance for displaying combat messages

    // --- Constructor ---
    public NPC(String name, Stats stats, List<String> dialogue, boolean isHostile, Item itemHeld, Object questToGive) {
        super(name, null, stats);
        this.dialogue = dialogue;
        this.isHostile = isHostile;
        this.itemHeld = itemHeld;
        this.questToGive = questToGive;
        this.isDead = false;
    }

    // --- NPC AI combat for hostile NPCs ---
    public void CPU_Attack(Player player) {
        if (isHostile && !isDead) {
            int damage = stats.getStrength(); // NPC attacks using its strength
            ui.displayMsg(getName() + " attacks " + player.getName() + " for " + damage + " damage!");
            player.takeDamage(damage);

            if (player.getCurrentHP() <= 0) {
                ui.displayMsg(player.getName() + " has been defeated by " + getName() + "!");
            }
        }
    }

    // --- Getters and setters ---
    public boolean isHostile() {
        return isHostile;
    }

    public void setHostile(boolean hostile) {
        isHostile = hostile;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
