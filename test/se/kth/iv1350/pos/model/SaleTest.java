package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.DatabaseUnavailableException;
import se.kth.iv1350.pos.integration.DiscountRegister;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Sale sale;
    private ExternalInventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        inventorySystem = ExternalInventorySystem.getExternalInventorySystem();
        DiscountRegister discountRegister = DiscountRegister.getDiscountRegister();
        sale = new Sale(discountRegister, inventorySystem);
    }

    @Test
    void testAddItemID() throws ItemNotFoundException, DatabaseUnavailableException {
        assertEquals(0, sale.getItems().size(), "Sale should be empty on start.");
        assertNotNull(sale.addItemID("TEST_ITEM", 1), "addItemID should not return Null.");
        assertEquals(100f, sale.getRunningTotal(), "Item should be added to Sale.");
        assertNotNull(sale.addItemID("TEST_ITEM", 1));
    }


    @Test
    void testApplyDiscounts() throws ItemNotFoundException, DatabaseUnavailableException {

        sale.addItemID("abc123", 1);
        assertEquals(29.9f, sale.getRunningTotal(), "Running total before discount.");
        sale.applyDiscounts("Customer_3333");
        assertEquals(16.91f, sale.getRunningTotal(), "Running total after discount.");
    }

    @Test
    void testCompleteSale() throws ItemNotFoundException, DatabaseUnavailableException {

        int amountToCheck = inventorySystem.getInventoryQuantity("abc123");
        sale.addItemID("abc123", 1);
        sale.completeSale();

        assertEquals(amountToCheck - 1, inventorySystem.getInventoryQuantity("abc123"), "Sale should have decremented Inventory.");
        assertNotEquals("", sale.actionsLog, "actionsLog should not be empty.");
    }
}