package util;

import collectibles.Item;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    private static Map<String, Item> registry = new HashMap<>();

    static {

    }

    public static Item create(String itemName) {
        Item prototype = registry.get(itemName);
        if (prototype != null) {
            return prototype.copy();   // use clone constructor
        } else {
            System.err.println("ERROR: Unknown item " + itemName + " requested from ItemRegistry.");
            return null;
        }
    }
}
