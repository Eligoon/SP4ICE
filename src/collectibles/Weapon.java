package collectibles;

import java.util.List;

public class Weapon extends Item {

    private int damage;

    // Normal constructor
    public Weapon(String itemName, String description, int weight, int damage) {
        super(itemName, description, weight);
        this.damage = damage;
    }

    // Copy constructor
    protected Weapon(Weapon other) {
        super(other);
        this.damage = other.damage;
    }

    // Required by Item
    @Override
    public Item copy() {
        return new Weapon(this);
    }

    public int getDamage() {
        return damage;
    }

    // ---------- weapon difinetions ----------

    public static Weapon dagger() {
        return new Weapon(
                "Dagger",
                "A small but quick blade.",
                1,
                2
        );
    }

    public static Weapon shortSword() {
        return new Weapon(
                "Short Sword",
                "A balanced blade for close combat.",
                2,
                3
        );
    }

    public static Weapon longSword() {
        return new Weapon(
                "Long Sword",
                "A versatile weapon favored by knights.",
                3,
                4
        );
    }

    public static Weapon battleAxe() {
        return new Weapon(
                "Battle Axe",
                "A heavy axe capable of devastating blows.",
                4,
                5
        );
    }

    public static Weapon warHammer() {
        return new Weapon(
                "War Hammer",
                "Designed to crush armor with raw force.",
                5,
                6
        );
    }

    public static Weapon spear() {
        return new Weapon(
                "Spear",
                "A long reach weapon ideal for keeping enemies at bay.",
                3,
                4
        );
    }

    public static Weapon elvenBow() {
        return new Weapon(
                "Elven Bow",
                "A finely crafted bow with deadly accuracy.",
                2,
                5
        );
    }

    public static Weapon orcishGreatsword() {
        return new Weapon(
                "Orcish Greatsword",
                "A massive blade forged for brutal strength.",
                6,
                7
        );
    }
    /*
    Example of use: iventory.addItem(Weapon.longSword());
     */

    // ---------- Helper Method ----------

    public static List<Weapon> getAllWeapons() {
        return List.of(
                dagger(),
                shortSword(),
                longSword(),
                battleAxe(),
                warHammer(),
                spear(),
                elvenBow(),
                orcishGreatsword()
        );
    }
}
