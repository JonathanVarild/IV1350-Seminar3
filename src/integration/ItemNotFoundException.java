package integration;

public class ItemNotFoundException extends Exception {
    private String itemID;

    public ItemNotFoundException(String itemID) {
        super(String.format("Item with ID (%S) could not be found.", itemID));
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }
}