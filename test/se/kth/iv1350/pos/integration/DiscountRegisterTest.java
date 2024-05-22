package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Discount;
import se.kth.iv1350.pos.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class DiscountRegisterTest {

    DiscountRegister discountRegister;

    @BeforeEach
    void setUp() {
        discountRegister = DiscountRegister.getDiscountRegister();
    }

    @Test
    void getDiscountRegisterTest() {
        assertNotNull(DiscountRegister.getDiscountRegister(), "GetDiscountRegister shall always return instance of DiscountRegister.");
    }

    @Test
    void getDiscountsTest() throws ItemNotFoundException {
        Item[] items = new Item[] {ExternalInventorySystem.getExternalInventorySystem().getItemInfo("TEST_ITEM")};
        Discount[] discounts = discountRegister.getDiscounts("TEST_CUSTOMER", items);
        assertEquals(discounts.length, 2, "There should be 2 types of discounts returned by getDiscounts for testing purposes.");
    }
}