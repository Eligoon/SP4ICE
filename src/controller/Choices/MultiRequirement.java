package controller.Choices;

import creatures.Player;
import java.util.List;

// Combines multiple requirements using AND or OR logic
public class MultiRequirement implements Requirement {

    // Requirements to check
    private List<Requirement> requirements;

    // How to combine them: AND = all must pass, OR = at least one must pass
    private ChoiceType operator;

    // Create a MultiRequirement with a list of requirements and an operator
    public MultiRequirement(List<Requirement> requirements, ChoiceType operator) {
        this.requirements = requirements;
        this.operator = operator;
    }

    // Check if the requirements are met for the given player
    @Override
    public boolean isMet(Player player) {
        if (operator == ChoiceType.AND) {
            // AND: fail if any requirement is not met
            for (Requirement req : requirements) {
                if (!req.isMet(player)) return false;
            }
            return true;
        } else { // OR
            // OR: succeed if any requirement is met
            for (Requirement req : requirements) {
                if (req.isMet(player)) return true;
            }
            return false;
        }
    }
}
