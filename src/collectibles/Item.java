package collectibles;

import creatures.Player;

public abstract class Item {

protected String itemName;
protected String description;
protected int weight;

    public Item(String itemName, String description, int weight) {
        this.itemName = itemName;
        this.description = description;
        this.weight = weight;
    }
    public abstract void use(Player player);

    public boolean isConsumable() {
        return false;
    }

    // Clone constructor
    protected Item(Item other) {
        this.itemName = other.itemName;
        this.description = other.description;
        this.weight = other.weight;
    }

    // Method to allow items to be copied to the ItemRegistry
    public abstract Item copy();

    public String getItemName() {
        return itemName;
    }

    public String getDescription(){
        return description;
    }

    public int getWeight(){
        return weight;
    }
}
