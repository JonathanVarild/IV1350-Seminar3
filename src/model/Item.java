package model;

public class Item {
    public String itemID;
    public String name;
    public String description;
    public float price;
    public int vatRate;
    public int quantity;

    public Item(String itemID, String name, String description, float price, int vatRate, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    public Item getZeroQuantityItem() {
        return new Item(this.itemID, this.name, this.description, this.price, this.vatRate, 0);
    }
}
