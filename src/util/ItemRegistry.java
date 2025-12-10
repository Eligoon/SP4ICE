package util;

import collectibles.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    // Registry map storing prototype items by name
    private static Map<String, Item> registry = new HashMap<>();

    // Initialize registry with all items in the game
    // run this once when the class is loaded, before anything else
    static {
        // Items will be added here later
    }

    // Create a new instance of an item by name
    public static Item create(String itemName) {
        Item prototype = registry.get(itemName);
        if (prototype != null) {
            return prototype.clone(); // clone to avoid sharing mutable state (part of java class Object)
        } else {
            System.err.println("ERROR: Unknown item " + itemName + " requested from ItemRegistry.");
            return null;
        }
    }
}