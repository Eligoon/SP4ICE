package collectibles;

import java.util.List;

public class Armor extends Item {

    private int defense;

    // Normal constructor
    public Armor(String itemName, String description, int weight, int defense) {
        super(itemName, description, weight);
        this.defense = defense;
    }

    // Copy constructor
    protected Armor(Armor other) {
        super(other);
        this.defense = other.defense;
    }

    // Required by Item (abstract)
    @Override
    public Item copy() {
        return new Armor(this);
    }

    public int getDefense() {
        return defense;
    }

    // ---------- Armor difinetions ----------

    public static Armor clothRobe() {
        return new Armor(
                "Cloth Robe",
                "Simple cloth robe offering minimal protection.",
                1,
                1
        );
    }

    public static Armor leatherArmor() {
        return new Armor(
                "Leather Armor",
                "Light armor made from hardened leather.",
                3,
                2
        );
    }

    public static Armor studdedLeather() {
        return new Armor(
                "Studded Leather",
                "Leather armor reinforced with metal studs.",
                4,
                3
        );
    }

    public static Armor chainmail() {
        return new Armor(
                "Chainmail",
                "Interlocking metal rings providing solid defense.",
                6,
                4
        );
    }

    public static Armor scaleArmor() {
        return new Armor(
                "Scale Armor",
                "Overlapping metal scales sewn onto leather.",
                7,
                5
        );
    }

    public static Armor breastplate() {
        return new Armor(
                "Breastplate",
                "Heavy metal chest armor offering strong protection.",
                8,
                6
        );
    }

    public static Armor fullPlate() {
        return new Armor(
                "Full Plate Armor",
                "Extremely heavy armor covering the entire body.",
                10,
                8
        );
    }

    public static Armor elvenArmor() {
        return new Armor(
                "Elven Armor",
                "Lightweight magical armor crafted by elves.",
                4,
                5
        );
    }

    // ---------- Helper method ----------
    //just to get hands on a list of armors

    public static List<Armor> getAllArmors() {
        return List.of(
                clothRobe(),
                leatherArmor(),
                studdedLeather(),
                chainmail(),
                scaleArmor(),
                breastplate(),
                fullPlate(),
                elvenArmor()
        );
    }
}