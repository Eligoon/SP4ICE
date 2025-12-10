package creatures.attributes;

import collectibles.Armor;
import collectibles.Weapon;
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


}
