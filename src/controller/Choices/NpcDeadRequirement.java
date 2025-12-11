package controller.Choices;

import creatures.NPC;
import creatures.Player;

public class NpcDeadRequirement implements Requirement {

    private NPC npc; // The NPC that must be dead

    public NpcDeadRequirement(NPC npc) {
        this.npc = npc;
    }

    @Override
    public boolean isMet(Player player) {
        return npc.isDead();
    }
}
