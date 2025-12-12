package creatures.attributes;

import collectibles.Armor;
import collectibles.Weapon;
import creatures.Player;
import util.TextUI;
import collectibles.Item;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int maxCapacity;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private TextUI ui = new TextUI();

    public Inventory(int maxCapacity){
        this.items = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public boolean addItem(Item item){
        if (items.size() >= maxCapacity){
            ui.displayMsg("Inventory is full! Connot add: " + item.getName());
            return false;
        }
        items.add(item);
        return true;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void displayInventory(){
        ui.displayMsg("=== INVENTORY ===");

        if(items.isEmpty()){
            ui.displayMsg("Inventory is empty! Hampster could not find anything!");
        }else{
            for ( int i = 0; i< items.size(); i++){
                ui.displayMsg((i+1) + ". " + items.get(i).getName());
            }
        }
        ui.displayMsg("------");
        ui.displayMsg("Equipped Weapon: " + (equippedWeapon != null ? equippedWeapon.getName() : "None"));
        ui.displayMsg("Equipped Armor: " + (equippedArmor !=null ? equippedArmor.getName() : "None"));
    }

    public boolean hasItem(String itemName){
        for (Item item : items){
            if (item.getName().equalsIgnoreCase(itemName)){
                return true;
                }
            }
        return false;
        }

        public List<Item> getItems(){
        return items;
        }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    // Extra: equip methods (not required by UML but needed in gameplay) The Code hamster thought it might be a good ida
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
    }

    private List<Weapon> getWeapons(){
        List<Weapon> weapons = new ArrayList<>();
        for(Item item : items){
            if(item instanceof Weapon){
                weapons.add((Weapon) item);
            }
        }
        return weapons;
    }

    private List<Armor> getArmors(){
        List<Armor> armors = new ArrayList<>();
        for (Item item : items){
            if(item instanceof Armor){
                armors.add((Armor) item);
            }
        }
        return armors;
    }

    public void equipWeapon(){
        List<Weapon> weapons = getWeapons();

        if (weapons.isEmpty()){
            ui.displayMsg("You have no weapons to equip!");
            return;
        }
        ui.displayMsg("Choose a weapon ot equip:");
        ArrayList<String> weaponNames = new ArrayList<>();

        for (Weapon w : weapons){
            weaponNames.add(w.getName());
        }
        int choice = ui.promptNumeric("Enter Number:") - 1;
        if (choice < 0 || choice >= weapons.size()){
            ui.displayMsg("Invalid choice!");
            return;
        }
        equippedWeapon = weapons.get(choice);
        ui.displayMsg("Equipped Weapon: " + equippedWeapon.getName());
    }

    public void equipArmor(){
        List<Armor> armors = getArmors();

        if (armors.isEmpty()){
            ui.displayMsg("You have no armor to equip!");
            return;
        }
        ui.displayMsg("Choose armor to equip:");
        ArrayList<String> armorNames = new ArrayList<>();

        for(Armor a : armors){
            armorNames.add(a.getName());
        }
        int choice = ui.promptNumeric("Enter number:") - 1;
        if(choice <0 || choice >=armors.size()){
            ui.displayMsg("Invalid choice!");
            return;
        }
        equippedArmor = armors.get(choice);
        ui.displayMsg("Equipped armor: " + equippedArmor.getName());
    }

    //to make this work, we would Need a: public void use(Player player) method

    public void useItem(Player Player){
        if (items.isEmpty()){
            ui.displayMsg("You have no items to use!");
            return;
        }

        ui.displayMsg("Choose an item to use:");

        ArrayList<String> itemNames = new ArrayList<>();
        for(Item i : items){
            itemNames.add(i.getName());
        }

        int choice = ui.promptNumeric("Enter number:") -1;

        if(choice <0 || choice >= items.size()){
            ui.displayMsg("Invalid choice!");
            return;
        }
        Item vhoosenItem = items.get(choice);

        //call the item's effect on the player
        chosenItem.user(player);

        //if item is consumable!, we remove it!
        if(chosenItem.isConsumable()){
            removeItem(chosenItem);
        }
    }


}
