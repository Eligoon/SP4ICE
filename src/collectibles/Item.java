package collectibles;

public abstract class Item {

protected String itemName;
protected String description;
protected int weight;

    public Item(String itemName, String description, int weight) {
        this.itemName = itemName;
        this.description = description;
        this.weight = weight;
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
}
