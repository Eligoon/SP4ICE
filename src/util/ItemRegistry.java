package util;

import collectibles.Item;
import java.util.HashMap;
import java.util.Map;

/**
 * ItemRegistry acts as a central factory for creating Items.
 *
 * It stores ONE prototype (template) instance of each item and
 * creates new copies when requested.
 *
 * This prevents shared-state bugs and keeps item creation consistent.
 */
public class ItemRegistry {

    /**
     * The registry map stores item prototypes.
     *
     * Key   = unique item name / ID (String)
     * Value = prototype Item (template, never given directly to players)
     */
    private static Map<String, Item> registry = new HashMap<>();

    /**
     * Static initialization block.
     *
     * This runs ONCE when the ItemRegistry class is first loaded.
     *
     * Use this block to register all game items by putting
     * their prototype instances into the registry map.
     *
     * Example:
     * registry.put("Iron Sword", new Weapon(...));
     * registry.put("Health Potion", new Consumable(...));
     */
    static {

    }

    /**
     * Creates a new Item instance based on its name.
     *
     * @param itemName The name / ID of the item to create
     * @return A NEW copy of the requested item, or null if not found
     */
    public static Item create(String itemName) {

        // Look up the prototype item from the registry
        Item prototype = registry.get(itemName);

        // If the prototype exists, return a COPY of it
        // (never return the prototype itself!)
        if (prototype != null) {
            return prototype.copy();   // Clone the prototype into a new instance
        }
        // If the item name is unknown, log an error
        else {
            System.err.println(
                    "ERROR: Unknown item " + itemName + " requested from ItemRegistry."
            );
            return null;
        }
    }
}

