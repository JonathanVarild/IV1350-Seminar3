package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.utilities.LoggedException;

/**
 * A checked exception for when a itemID cannot be found.
 */
public class ItemNotFoundException extends LoggedException {
    private String itemID;

    /**
     * Constructor for the ItemNotFoundException.
     *
     * @param itemID The itemID that couldn't be found.
     */
    public ItemNotFoundException(String itemID) {
        super(String.format("Item with ID (%S) could not be found.", itemID));
        this.itemID = itemID;
    }

    /**
     * Returns the itemID of the item that couldn't be found.
     *
     * @return The itemID that couldn't be found.
     */
    public String getItemID() {
        return itemID;
    }
}