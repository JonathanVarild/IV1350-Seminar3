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
     *
     * @throws ItemNotFoundException if the provided itemID cannot be found in the External Inventory System.
     * @throws DatabaseUnavailableException if the External Inventory System is unavailable.
     */
    public Item getItemInfo(String itemID) throws ItemNotFoundException {

        if (itemID.equals("MOCK_DATABASE_ERROR")) {
            throw new DatabaseUnavailableException("External Inventory System");
        }

        Item item = inventory.get(itemID);

        if (item != null) {
            return item.getZeroQuantityItem();
        }

        throw new ItemNotFoundException(itemID);
    }

    /**
     * Takes away a quantity of the item from the inventory.
     * 
     * @param itemID The item ID.
     * @param quantity The quantity to reduce the inventory by.
     *
     * @throws ItemNotFoundException if the provided itemID cannot be found in the External Inventory System.
     * @throws DatabaseUnavailableException if the External Inventory System is unavailable.
     */
    public void reduceInventory(String itemID, int quantity) throws ItemNotFoundException {
        if (itemID.equals("MOCK_DATABASE_ERROR")) {
            throw new DatabaseUnavailableException("External Inventory System");
        }
        else if (!inventory.containsKey(itemID)) {
            throw new ItemNotFoundException(itemID);
        }
        inventory.get(itemID).quantity -= quantity;
    }

    /**
     * Adds a quantity of the item from the inventory.
     *
     * @param itemID The item ID.
     * @param quantity The quantity to increment the inventory by.
     *
     * @throws ItemNotFoundException if the provided itemID cannot be found in the External Inventory System.
     * @throws DatabaseUnavailableException if the External Inventory System is unavailable.
     */
    public void incrementInventory(String itemID, int quantity) throws ItemNotFoundException {
        if (itemID.equals("MOCK_DATABASE_ERROR")) {
            throw new DatabaseUnavailableException("External Inventory System");
        }
        else if (!inventory.containsKey(itemID)) {
            throw new ItemNotFoundException(itemID);
        }
        inventory.get(itemID).quantity += quantity;
    }

    /**
     * Method used to get the inventory quantity of a specified item.
     *
     * @param itemID The itemID.
     *
     * @return The number of items of this itemID that is in stock.
     *
     * @throws ItemNotFoundException if the itemID cannot be found.
     * @throws DatabaseUnavailableException if the External Inventory System is unavailable.
     */
    public int getInventoryQuantity(String itemID) throws ItemNotFoundException {
        if (itemID.equals("MOCK_DATABASE_ERROR")) {
            throw new DatabaseUnavailableException("External Inventory System");
        }
        else if (!inventory.containsKey(itemID)) {
            throw new ItemNotFoundException(itemID);
        }
        return inventory.get(itemID).quantity;
    }

}
