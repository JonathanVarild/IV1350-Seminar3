package integration;

import model.Item;

import java.util.HashMap;

public class ExternalInventorySystem {

    private HashMap<String, Item> inventory = new HashMap<>();

    public ExternalInventorySystem() {

        inventory.put("abc123", new Item("abc123", "BigWheel Oatmeal", "BigWheel Oatmeal 500g, whole grain oats, high fiber , gluten free", 29.9f, 6, 22));
        inventory.put("def456", new Item("def456", "YouGoGo Blueberry", "YouGoGo Blueberry 240g, low sugar youghurt, blueberry flavour", 14.9f, 6, 53));

    }

    public Item getItemInfo(String itemID) {
        Item item = inventory.get(itemID);

        if (item != null) {
            return item.getZeroQuantityItem();
        }

        return null;
    }

    public void reduceInventory(String itemID, int quantity) {
        inventory.get(itemID).quantity -= quantity;
    }

}
