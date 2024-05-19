package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    public void testItemConstructor() {
        Item item = new Item("abc123", "Äpple", "Ett smarrigt äpple.", 0.75f, 10, 5);
        assertEquals("abc123", item.itemID, "Should contain value 'abc123'.");
        assertEquals("Äpple", item.name, "Should contain value 'Äpple'.");
        assertEquals("Ett smarrigt äpple.", item.description, "Should contain description 'Ett smarrigt äpple.'");
        assertEquals(0.75f, item.price, "Should hold the value 0.75 float.");
        assertEquals(10, item.vatRate, "Should hold the value 10.");
        assertEquals(5, item.quantity, "Should hold the value 5.");
    }

    @Test
    void getZeroQuantityItem() {
        Item item = new Item("abc123", "Äpple", "Ett smarrigt äpple.", 0.75f, 10, 5);
        Item result = item.getZeroQuantityItem();
        assertEquals(result.quantity, 0, "Should be zero and not what was set in its constructor.");
    }
}