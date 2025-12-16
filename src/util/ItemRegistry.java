package util;

import collectibles.Item;
import java.util.HashMap;
import java.util.Map;

import static collectibles.Armor.*;
import static collectibles.Health.*;
import static collectibles.Weapon.*;

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
        // Quest items
        Item heart_of_the_siren = new Item("Heart of the Siren","Obtained by killing the Siren", 1);
        registry.put("Heart of the Siren",heart_of_the_siren);

        Item cabin_key = new Item("Cabin Key","Cabin key obtained by killing the merchant", 1);
        registry.put("cabin_key", cabin_key);

        Item dire_wolf_head = new Item("Head of the Dire Wolf","Obtained by killing the Dire Wolf", 2);
        registry.put("dire_wolf_head",dire_wolf_head);

        Item heart_of_the_bog = new Item("Heart of the Bog", "Obtained by killing one of the Orc", 1);
        registry.put("heart_of_the_bog", heart_of_the_bog);

        Item heart_of_the_flower = new Item("Heart of the Flower", "Obtained by defeating the plant in the Clearing", 1);
        registry.put("heart_of_the_flower", heart_of_the_flower);

        // Health items
        registry.put("minor_potion",minorPotion());
        registry.put("lesser_potion",lesserPotion());
        registry.put("standard_potion", standardPotion());
        registry.put("greater_potion", greaterPotion());
        registry.put("superior_potion", superiorPotion());
        registry.put("elixir_of_life", elixirOfLife());

        // Armor items
        registry.put("cloth_robe", clothRobe());
        registry.put("leather_armor", leatherArmor());
        registry.put("studded_leather", studdedLeather());
        registry.put("chain_mail", chainmail());
        registry.put("scale_armor", scaleArmor());
        registry.put("breast_plate", breastplate());
        registry.put("full_plate", fullPlate());
        registry.put("elven_armor", elvenArmor());

        // Weapon items
        registry.put("dagger",dagger());
        registry.put("short_sword", shortSword());
        registry.put("long_sword", longSword());
        registry.put("battle_axe", battleAxe());
        registry.put("war_hammer", warHammer());
        registry.put("spear", spear());
        registry.put("elven_bow", elvenBow());
        registry.put("orcish_greatsword", orcishGreatsword());



    }

    /**
     * Creates a new Item instance based on its name.
     *
     * parameter: itemName The name / ID of the item to create
     * returns: A NEW copy of the requested item, or null if not found
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

