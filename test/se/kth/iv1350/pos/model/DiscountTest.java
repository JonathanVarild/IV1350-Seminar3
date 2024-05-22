package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    Discount itemDiscount;
    Discount generalDiscount;

    ExternalInventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        generalDiscount = new Discount(new FlatRateDiscount(50));
        itemDiscount = new Discount(new PercentageDiscount(10), "TEST_ITEM");

        inventorySystem = ExternalInventorySystem.getExternalInventorySystem();
    }

    /**
     * Test to check that an item specific discount returns the correct itemID.
     */
    @Test
    void getItemIDItemDiscountTest() {
        assertEquals(itemDiscount.getItemID(), "TEST_ITEM", "Method getItemID should return 'TEST_ITEM' for the item-specific discount.");
    }

    /**
     * Test to check that a general discount returns null for the itemID.
     */
    @Test
    void getItemIDGeneralDiscountTest() {
        assertNull(generalDiscount.getItemID(), "Method getItemID should return null for a general discount.");
    }

    /**
     * Test to check that a general discount returns the correct price reduction when calling getTotalPriceReduction.
     */
    @Test
    void getTotalPriceReductionGeneralDiscountTest() {
        float priceReduction = generalDiscount.getTotalPriceReduction(100);
        assertEquals(priceReduction, 50, "Method getTotalPriceReduction should return 50 SEK for the general discount of 50 SEK off 100 SEK.");
    }

    /**
     * Test to check that a general discount returns zero price reduction when calling getItemPriceReduction.
     */
    @Test
    void getItemPriceReductionGeneralDiscountTest() throws ItemNotFoundException {
        float priceReduction = generalDiscount.getItemPriceReduction(inventorySystem.getItemInfo("TEST_ITEM"));
        assertEquals(priceReduction, 0, "Method getItemPriceReduction should return 0 SEK for a general discount.");
    }


    /**
     * Test to check that an item specific discount returns the correct price reduction when calling getItemPriceReduction.
     */
    @Test
    void getItemPriceReductionItemDiscountTest() throws ItemNotFoundException {
        float priceReduction = itemDiscount.getItemPriceReduction(inventorySystem.getItemInfo("TEST_ITEM"));
        assertEquals(priceReduction, 10, "Method getItemPriceReduction should return 10 SEK for the item-specific discount on 'TEST_ITEM'.");
    }

    /**
     * Test to check that an item specific discount returns zero price reduction when calling getTotalPriceReduction.
     */
    @Test
    void getTotalPriceReductionItemDiscountTest() {
        float priceReduction = itemDiscount.getTotalPriceReduction(100);
        assertEquals(priceReduction, 0, "Method getTotalPriceReduction should return 0 SEK for an item-specific discount.");
    }
}