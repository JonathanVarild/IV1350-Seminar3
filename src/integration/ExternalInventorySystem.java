package integration;

import model.Item;

import java.util.HashMap;

public class ExternalInventorySystem {

    private HashMap<String, Item> inventory = new HashMap<>();

    /**
     * Creates a new instance of the ExternalInventorySystem class.
     * This class instance represents a virtual version of the external inventory system.
     */
    public ExternalInventorySystem() {
        inventory.put("abc123", new Item("abc123", "BigWheel Oatmeal", "BigWheel Oatmeal 500g, whole grain oats, high fiber , gluten free", 29.9f, 6, 22));
        inventory.put("def456", new Item("def456", "YouGoGo Blueberry", "YouGoGo Blueberry 240g, low sugar youghurt, blueberry flavour", 14.9f, 6, 53));
    }

    /**
     * Gets information from an itemID.
     * 
     * @param itemID The item ID.
     * 
     * @return A new instance of the Item class with the item information. Null if the item ID does not exist.
     */
    public Item getItemInfo(String itemID) {
        Item item = inventory.get(itemID);

        if (item != null) {
            return item.getZeroQuantityItem();
        }

        return null;
    }

    /**
     * Takes away a quantity of the item from the inventory.
     * 
     * @param itemID The item ID.
     * @param quantity The quantity to reduce the inventory by.
     */
    public void reduceInventory(String itemID, int quantity) {
        inventory.get(itemID).quantity -= quantity;
    }

}
