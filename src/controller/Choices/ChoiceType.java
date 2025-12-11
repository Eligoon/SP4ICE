package controller.Choices;

// This is a list of the different types of actions a Choice can be
public enum ChoiceType {
    MOVE,       // Go to another location
    COMBAT,     // Start a fight
    INTERACT,   // Use something or talk to something
    USEOBJECT, // Use an item

    AND,       // For multiRequirement
    OR,       // For multiRequirement
    // can add more here
}
