package model;

import integration.ExternalInventorySystem;

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

    public Sale(ExternalInventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
        time = LocalDateTime.now();
    }

    public Item addItemID(String itemID, int quantity) {
        boolean isScanned = isItemScanned(itemID);
        Item item;

        if (!isScanned) {
            item = inventorySystem.getItemInfo(itemID);

            if (item != null) {
                addItem(item, quantity);
                return item;
            }
        }
        else {
            incrementItemQuantity(itemID, quantity);
            return items.get(itemID);
        }

        return null;
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

    public void applyDiscounts(int customerID) {

    }

    public String completeSale() {
        String actionsLog = "";

        for (Map.Entry<String, Item> entry : items.entrySet()) {
            String itemID = entry.getKey();
            Item item = entry.getValue();

            inventorySystem.reduceInventory(itemID, item.quantity);

            actionsLog += String.format("Told external inventory system to decrease inventory quantity of item %s by %d units.\n", item.itemID, item.quantity);
        }

        return actionsLog;
    }
}
