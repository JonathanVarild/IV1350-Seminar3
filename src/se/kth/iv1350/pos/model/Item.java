package se.kth.iv1350.pos.model;

public class Item {
    public String itemID;
    public String name;
    public String description;
    public float price;
    public int vatRate;
    public int quantity;

    /**
     * Creates a new instance of the Item class.
     * The Item class represents an item that can be sold or that exists in the inventory.
     * 
     * @param itemID The item ID.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param price The price of the item.
     * @param vatRate The VAT rate of the item.
     * @param quantity The quantity of the item.
     */
    public Item(String itemID, String name, String description, float price, int vatRate, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    /**
     * Gets a new instance of the Item class with a quantity of 0.
     * 
     * @return A new instance of the Item class with a quantity of 0.
     */
    public Item getZeroQuantityItem() {
        return new Item(this.itemID, this.name, this.description, this.price, this.vatRate, 0);
    }
}
