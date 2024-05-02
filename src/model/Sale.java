package model;

import integration.ExternalInventorySystem;

import java.time.LocalTime;
import java.util.HashMap;

public class Sale {

    private ExternalInventorySystem inventorySystem;

    public LocalTime time;
    private HashMap<String, Item> items = new HashMap<>();
    private float runningTotal;
    private float totalVAT;

    public Sale(ExternalInventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
    }

    public boolean addItemID(String itemID, int quantity) {
        boolean isScanned = isItemScanned(itemID);
        Item item;

        if (!isScanned) {
            item = inventorySystem.getItemInfo(itemID);

            if (item != null) {
                addItem(item, quantity);
                return true;
            }
        }
        else {
            incrementItemQuantity(itemID, quantity);
            return true;
        }

        return false;
    }

    private void addItem(Item item, int quantity) {
        item.quantity += quantity;
        items.put(item.itemID, item);
    }

    private boolean isItemScanned(String itemID) {
        return items.containsKey(itemID);
    }

    private void incrementItemQuantity(String itemID, int quantity) {
        items.get(itemID).quantity += quantity;
    }

    public float getRunningTotal() {
        return runningTotal;
    }

    public float getTotalVAT() {
        return totalVAT;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void applyDiscounts(int customerID) {

    }

    public void completeSale() {
        items.forEach( (key, value) -> inventorySystem.reduceInventory(key, value.quantity));
    }
}
