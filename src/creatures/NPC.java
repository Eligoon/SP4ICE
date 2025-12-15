package creatures;

import collectibles.Item;
import collectibles.Quest;
import creatures.attributes.Stats;
import util.TextUI;
import java.util.List;
import creatures.attributes.AttackStat;
import world.Story;

public class NPC extends Creature {
    private String NPC_ID;             // NPC Identifer s.t unique interactions can happen
    private List<String> dialogue;     // Lines the NPC can say
    private Quest questToGive;        // Placeholder for a Quest class
    private Item itemHeld;             // Item the NPC is holding
    private boolean isHostile;         // Determines if NPC will attack player
    private boolean isDead;            // Tracks if NPC is dead
    private AttackStat attackStat;     // Enum stats to help for NPC combat
    private String originalLocation;   // Original location for NPC, needed for despawn

    private boolean despawnsWhenLeaving = false; // For a specific NPC type at a specific location

    private TextUI ui = new TextUI();  // TextUI instance for displaying combat messages

    // --- Constructor ---
    public NPC(String name, String NPC_ID, Stats stats, List<String> dialogue,
               boolean isHostile, Item itemHeld, Quest questToGive) {
        super(name, null, stats);
        this.NPC_ID = NPC_ID;
        this.dialogue = dialogue;
        this.isHostile = isHostile;
        this.itemHeld = itemHeld;
        this.questToGive = questToGive;
        this.isDead = false;
        this.attackStat = AttackStat.STRENGTH; // Defaulting to strength

    }

    // Stats for combat
    private int getAttackStatValue() {
        int str = stats.getStrength();
        int dex = stats.getDexterity();
        int intel = stats.getIntelligence();

        switch (attackStat) {
            case STRDEX:
                return str + dex;

            case INTSTR:
                return intel + str;

            case DEXINT:
                return dex + intel;

            case DEXTERITY:
                return dex;

            case INTELLIGENCE:
                return intel;

            case STRENGTH:
            default:
                return str;
        }
    }


    // --- NPC AI combat for hostile NPCs ---
    public void CPU_Attack(Player player) {
        if (isHostile && !isDead) {
            int damage = getAttackStatValue();

            ui.displayMsg(getName() + " attacks " + player.getName()
                    + " for " + damage + " damage!");

            player.takeDamage(damage);

            if (player.getCurrentHP() <= 0) {
                ui.displayMsg(player.getName()
                        + " has been defeated by " + getName() + "!");
            }
        }
    }

    // In NPC.java
    @Override
    public void interactWithPlayer(Player player, Story story, TextUI ui) {
        List<String> dialogueLines = story.getDialogueForNPC(this, player); // use Story class

        for (String line : dialogueLines) {
            ui.displayMsg(this.getName() + " says: " + line);
        }

        // Give quest if available
        if (questToGive != null && !player.hasQuest(questToGive.getQuestId())) {
            giveQuest(player);
        }

        // Give held item if available
        if (itemHeld != null) {
            player.pickUpItem(itemHeld);
            ui.displayMsg(getName() + " gives you " + itemHeld.getItemName());
            itemHeld = null;
        }

        // Hostile NPC check
        if (isHostile()) {
            ui.displayMsg(getName() + " seems hostile!");
            // You can call combat directly if needed
        }
    }

    // --- Despawn getter/setter ---
    public boolean doesDespawnWhenLeaving() {
        return despawnsWhenLeaving;
    }

    public void setDespawnWhenLeaving(boolean value) {
        this.despawnsWhenLeaving = value;
    }

    // Method to add dialogue, check story class
    public void speak(String text){
        ui.displayMsg(name + " says: " + text);
    }

    // Give quest to player
    public void giveQuest(Player player) {
        if (questToGive != null && !player.addQuest(questToGive.getQuestId())) {
            player.addQuest(questToGive);
            ui.displayMsg(name + " gives you a quest: " + questToGive.getQuestName());
        }
    }

    // Give reward for turning in quest
    public void giveReward(Player player, Item reward) {
        if (reward != null) {
            player.pickUpItem(reward);
            System.out.println(name + " gives you " + reward.getItemName());
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
        return getCurrentHP() <= 0;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setAttackStat(AttackStat attackStat) {
        this.attackStat = attackStat;
    }

    public String getOriginalLocation() {
        return originalLocation;
    }

    public void setOriginalLocation(String originalLocation) {
        this.originalLocation = originalLocation;
    }

}
