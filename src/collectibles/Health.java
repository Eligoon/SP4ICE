package collectibles;

import creatures.Player;

//Hope that Player have something like: public void heal(int amount)  Else this might not work!

public class Health extends Item {

    private int healAmount;

    // Normal constructor
    public Health(String itemName, String description, int weight, int healAmount) {
        super(itemName, description, weight);
        this.healAmount = healAmount;
    }

    // Copy constructor
    protected Health(Health other) {
        super(other);
        this.healAmount = other.healAmount;
    }

    @Override
    public Item copy() {
        return new Health(this);
    }

    // Heal the player
    public void use(Player player) {
        player.heal(healAmount);
    }

    // Health items are consumable
    public boolean isConsumable() {
        return true;
    }

    public int getHealAmount() {
        return healAmount;
    }

    // ---------- Health items ----------

    public static Health minorPotion() {
        return new Health(
                "Minor Healing Potion",
                "Restores a small amount of health.",
                1,
                10
        );
    }

    public static Health lesserPotion() {
        return new Health(
                "Lesser Healing Potion",
                "Restores some health.",
                1,
                20
        );
    }

    public static Health standardPotion() {
        return new Health(
                "Healing Potion",
                "A standard healing potion.",
                1,
                30
        );
    }

    public static Health greaterPotion() {
        return new Health(
                "Greater Healing Potion",
                "Restores a large amount of health.",
                2,
                50
        );
    }

    public static Health superiorPotion() {
        return new Health(
                "Superior Healing Potion",
                "Restores a very large amount of health.",
                2,
                75
        );
    }

    public static Health elixirOfLife() {
        return new Health(
                "Elixir of Life",
                "A rare elixir that restores massive health.",
                3,
                100
        );
    }

    //example of use in game: inventory.addItem(health.minorPotion());
    //inventory.useItem(player);
    /*
    Health is a specialized item, that can be used on a player. When it is used it call the heal() on the player,
    and since it is a consumable, it will remove itself.
     */
}
