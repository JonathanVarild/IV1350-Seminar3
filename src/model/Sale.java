package model;

import java.time.LocalTime;

public class Sale {

    public LocalTime time;
    private Item[] items;
    private float runningTotal;
    private float totalVAT;

    public boolean addItemID(int itemID, int quantity) {
        return true;
    }

    private void addItem(Item item, int quantity) {

    }

    private boolean isItemScanned(int itemID) {
        return true;
    }

    private void incrementItemQuantity(int itemID) {

    }

    public float getRunningTotal() {
        return runningTotal;
    }

    public float getTotalVAT() {
        return totalVAT;
    }

    public Item[] getItems() {
        return items;
    }

    public void applyDiscounts(int customerID) {

    }

    public void completeSale() {

    }
}
