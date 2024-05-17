package model;

import integration.DatabaseUnavailableException;
import integration.ExternalInventorySystem;
import integration.ItemNotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Sale {

    private ExternalInventorySystem inventorySystem;

    public LocalDateTime time;
    private HashMap<String, Item> items = new HashMap<>();
    private float runningTotal = 0f;
    private float totalVAT = 0f;
    String actionsLog = "";

    /**
     * Creates a new instance of the Sale class.
     * 
     * @param inventorySystem The external inventory system.
     */
    public Sale(ExternalInventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
        time = LocalDateTime.now();
    }

    /**
     * Adds an item to the sale by its ID and a quantity.
     * If the item is already added, it increments the quantity by the given quantity.
     * 
     * @param itemID The item ID.
     * @param quantity The quantity of the item.
     * 
     * @return The item that was added to the sale.
     *
     * @throws ItemNotFoundException if the itemID couldn't be found
     */
    public Item addItemID(String itemID, int quantity) throws ItemNotFoundException {
        boolean isScanned = isItemScanned(itemID);
        Item item;

        if (!isScanned) {
            item = inventorySystem.getItemInfo(itemID);
            addItem(item, quantity);
            return item;
        }
        else {
            incrementItemQuantity(itemID, quantity);
            return items.get(itemID);
        }
    }

    private void addItem(Item item, int quantity) {
        item.quantity += quantity;
        items.put(item.itemID, item);

        updateTotals(item.price, item.vatRate, quantity);
    }

    private boolean isItemScanned(String itemID) {
        return items.containsKey(itemID);
    }

    private void incrementItemQuantity(String itemID, int quantity) {
        Item item = items.get(itemID);

        item.quantity += quantity;

        updateTotals(item.price, item.vatRate, quantity);
    }

    private void updateTotals(float price, int vatRate, int quantity) {
        runningTotal += price * quantity;
        totalVAT += price * ((float)vatRate / 100) * quantity;
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

    /**
     * Applies discounts to the sale based on the customer ID.
     * 
     * @param customerID The customer ID.
     */
    public void applyDiscounts(int customerID) {

    }

    /**
     * Used to signal that the sale is complete and that the inventory should be reduced.
     */
    public void completeSale() throws ItemNotFoundException{
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            String itemID = entry.getKey();
            Item item = entry.getValue();

            try {
                inventorySystem.reduceInventory(itemID, item.quantity);
            }
            catch (ItemNotFoundException e) {
                for (Map.Entry<String, Item> entry2 : items.entrySet()) {
                    String itemID2 = entry.getKey();
                    Item item2 = entry.getValue();

                    if (itemID2.equals(e.getItemID()))
                        throw e;

                    inventorySystem.incrementInventory(itemID2, item2.quantity);
                }
            }

            actionsLog += String.format("Told external inventory system to decrease inventory quantity of item %s by %d units.\n", item.itemID, item.quantity);
        }
    }

    public String getActionsLog() {
        return actionsLog;
    }
}
