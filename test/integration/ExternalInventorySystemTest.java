package integration;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.ErrorLogger;

import static org.junit.jupiter.api.Assertions.*;

class ExternalInventorySystemTest {

    private ExternalInventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        inventorySystem = new ExternalInventorySystem();
        ErrorLogger.setupLoggingSystem("test_errors.txt");
    }

    @Test
    void testGetItemInfoItemExists() {
        try {
            Item item = inventorySystem.getItemInfo("abc123");
            assertEquals(item.itemID,"abc123", "ItemID of Item class should be equal to 'abc123' when retrieving itemID 'abc123'.");
        }
        catch (ItemNotFoundException e) {
            fail("ItemNotFoundException should not have been thrown for ItemID 'abc123'.");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testGetItemInfoNotExists() {
        try {
            inventorySystem.getItemInfo("NON_EXISTENT_ID");
            fail("Should have thrown ItemNotFoundException.");
        }
        catch (ItemNotFoundException e) {
            assertEquals("NON_EXISTENT_ID", e.getItemID(), "ItemNotFoundException should return 'NON_EXISTENT_ID' when calling getItemID().");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testGetItemInfoDatabaseUnavailable() {
        try {
            inventorySystem.getItemInfo("MOCK_DATABASE_ERROR");
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (ItemNotFoundException e) {
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (DatabaseUnavailableException e) {
            assertEquals(e.getDatabaseName(), "External Inventory System", "Unavailable database should be 'External Inventory System'.");
        }
    }

    @Test
    void testReduceInventoryItemExists() {
        try {
            int reduction = 1;
            int expectedQuantity = inventorySystem.getInventoryQuantity("abc123") - reduction;
            inventorySystem.reduceInventory("abc123", reduction);
            assertEquals(expectedQuantity, inventorySystem.getInventoryQuantity("abc123"), String.format("Item quantity should be reduced by %d.", reduction));
        }
        catch (ItemNotFoundException e) {
            fail("ItemNotFoundException should not have been thrown for ItemID 'abc123'.");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testReduceInventoryNotExists() {
        try {
            inventorySystem.reduceInventory("NON_EXISTENT_ID",1);
            fail("Should have thrown ItemNotFoundException.");
        }
        catch (ItemNotFoundException e) {
            assertEquals("NON_EXISTENT_ID", e.getItemID(), "ItemNotFoundException should return 'NON_EXISTENT_ID' when calling getItemID().");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testReduceInventoryDatabaseUnavailable() {
        try {
            inventorySystem.reduceInventory("MOCK_DATABASE_ERROR", 1);
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (ItemNotFoundException e) {
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (DatabaseUnavailableException e) {
            assertEquals(e.getDatabaseName(), "External Inventory System", "Unavailable database should be 'External Inventory System'.");
        }
    }

    @Test
    void incrementInventoryItemExists() {
        try {
            int incrementation = 1;
            int expectedQuantity = inventorySystem.getInventoryQuantity("abc123") + incrementation;
            inventorySystem.incrementInventory("abc123", incrementation);
            assertEquals(expectedQuantity, inventorySystem.getInventoryQuantity("abc123"), String.format("Item quantity should be incremented by %d.", incrementation));
        }
        catch (ItemNotFoundException e) {
            fail("ItemNotFoundException should not have been thrown for ItemID 'abc123'.");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void incrementInventoryNotExists() {
        try {
            inventorySystem.incrementInventory("NON_EXISTENT_ID",1);
            fail("Should have thrown ItemNotFoundException.");
        }
        catch (ItemNotFoundException e) {
            assertEquals("NON_EXISTENT_ID", e.getItemID(), "ItemNotFoundException should return 'NON_EXISTENT_ID' when calling getItemID().");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void incrementInventoryDatabaseUnavailable() {
        try {
            inventorySystem.incrementInventory("MOCK_DATABASE_ERROR", 1);
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (ItemNotFoundException e) {
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (DatabaseUnavailableException e) {
            assertEquals(e.getDatabaseName(), "External Inventory System", "Unavailable database should be 'External Inventory System'.");
        }
    }

    @Test
    void getInventoryQuantityItemExists() {
        try {
            inventorySystem.getInventoryQuantity("abc123");
        }
        catch (ItemNotFoundException e) {
            fail("ItemNotFoundException should not have been thrown for ItemID 'abc123'.");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getInventoryQuantityNotExists() {
        try {
            inventorySystem.getInventoryQuantity("NON_EXISTENT_ID");
            fail("Should have thrown ItemNotFoundException.");
        }
        catch (ItemNotFoundException e) {
            assertEquals("NON_EXISTENT_ID", e.getItemID(), "ItemNotFoundException should return 'NON_EXISTENT_ID' when calling getItemID().");
        }
        catch (DatabaseUnavailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getInventoryQuantityDatabaseUnavailable() {
        try {
            inventorySystem.getInventoryQuantity("MOCK_DATABASE_ERROR");
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (ItemNotFoundException e) {
            fail("Should have thrown DatabaseUnavailableException.");
        }
        catch (DatabaseUnavailableException e) {
            assertEquals(e.getDatabaseName(), "External Inventory System", "Unavailable database should be 'External Inventory System'.");
        }
    }
}