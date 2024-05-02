package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    public void testItemConstructor() {
        Item item = new Item("123ABC", "Apple", "A tasty apple", 0.75f, 10, 5);
        assertEquals("123ABC", item.itemID);
        assertEquals("Apple", item.name);
        assertEquals("A tasty apple", item.description);
        assertEquals(0.75f, item.price, 0.001);
        assertEquals(10, item.vatRate);
        assertEquals(5, item.quantity);
    }
}