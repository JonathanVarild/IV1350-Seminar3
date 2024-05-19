package model;

import integration.DatabaseUnavailableException;
import integration.DiscountRegister;
import integration.ExternalInventorySystem;
import integration.ItemNotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Sale {

    private ExternalInventorySystem inventorySystem;
    private DiscountRegister discountRegister;

    public LocalDateTime time;
    private HashMap<String, Item> items = new HashMap<>();
    private float runningTotal = 0f;
    private float totalVAT = 0f;
    private float totalDiscount = 0f;
    String actionsLog = "";

    /**
     * Creates a new instance of the Sale class.
     * 
     * @param inventorySystem The external inventory system.
     */
    public Sale(DiscountRegister discountRegister, ExternalInventorySystem inventorySystem) {
        this.discountRegister = discountRegister;
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

    /**
     * Method to add an item based on a quantity.
     *
     * @param item The item class to add.
     * @param quantity The quantity to add.
     */
    private void addItem(Item item, int quantity) {
        item.quantity += quantity;
        items.put(item.itemID, item);

        updateTotals(item.price, item.vatRate, quantity);
    }

    /**
     * Method to check if an item has been scanned.
     *
     * @param itemID The itemID to check.
     *
     * @return Boolean if an item with the same itemID has been scanned or not.
     */
    private boolean isItemScanned(String itemID) {
        return items.containsKey(itemID);
    }

    /**
     * Method used to increment the item quantity of a certain item.
     *
     * @param itemID The itemID to increment the quantity of.
     * @param quantity The quantity to increment by.
     */
    private void incrementItemQuantity(String itemID, int quantity) {
        Item item = items.get(itemID);

        item.quantity += quantity;

        updateTotals(item.price, item.vatRate, quantity);
    }

    /**
     * Method used to update the totals runningTotal and totalVAT.
     *
     * @param price The price to add.
     * @param vatRate The vatRate to base the VAT calculations on.
     * @param quantity The quantity of items.
     */
    private void updateTotals(float price, int vatRate, int quantity) {
        runningTotal += price * quantity;
        totalVAT += price * ((float)vatRate / 100) * quantity;
    }

    /**
     * Method used to get the total price.
     *
     * @return The total price.
     */
    public float getRunningTotal() {
        return runningTotal;
    }

    /**
     * Method used to get the total VAT calculated.
     *
     * @return The total VAT.
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * Method used to get the total price reduction from discounts.
     *
     * @return Total price reduction.
     */
    public float getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Method used to get the scanned items as an hashmap.
     *
     * @return Scanned items as an hashmap.
     */
    public HashMap<String, Item> getItems() {
        return items;
    }

    /**
     * Method used to get the scanned items as an array.
     *
     * @return Scanned items as an array.
     */
    public Item[] getItemsArray() {
        return items.values().toArray(new Item[0]);
    }

    /**
     * Applies discounts to the sale based on the customer ID.
     * 
     * @param customerID The customer ID.
     */
    public Discount[] applyDiscounts(String customerID) {
        Discount[] discounts = discountRegister.getDiscounts(customerID, getItemsArray());

        for (Discount discount : discounts) {

            if (discount.getItemID() == null) {
                float discountReduction = discount.getTotalPriceReduction(runningTotal);
                runningTotal -= discountReduction;
                totalDiscount += discountReduction;
            }
            else {
                Item item = items.get(discount.getItemID());
                float discountReduction = discount.getItemPriceReduction(item);
                updateTotals(-discountReduction,item.vatRate,item.quantity);
                totalDiscount += discountReduction;
            }
        }

        return discounts;
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

    /**
     * Used to get the actions log for what has been done within the sale.
     *
     * @return Log summary as a string.
     */
    public String getActionsLog() {
        return actionsLog;
    }
}
