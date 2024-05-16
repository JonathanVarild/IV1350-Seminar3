package model;

import integration.ExternalInventorySystem;
import integration.ItemNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;


class SaleTest {

    private Sale sale;
    private ExternalInventorySystem fakeInventorySystem;
    private Item testItem;

    /*@BeforeEach
    void setUp() {
        fakeInventorySystem = mock(ExternalInventorySystem.class);
        sale = new Sale(fakeInventorySystem);
        testItem = new Item("XYZ123", "Pre-peeled banana","Like a banana but without peel",10.99f,10,100);

        try {
            when(fakeInventorySystem.getItemInfo("XYZ123")).thenReturn(testItem);
        }
        catch (ItemNotFoundException e) {
            // Todo later
        }

        sale.addItemID("XYZ123",2);
    }

    @AfterEach
    void reset() {
        fakeInventorySystem = null;
        sale = null;
        testItem = null;
    }

    @Test
    void testAddItemID() {
        assertEquals( 21.98f,sale.getRunningTotal(),"Running total should be 21.98");
        assertEquals(testItem, sale.addItemID("XYZ123", 2),"Item object in sale should match testItem");
    }

    @Test
    void testCompleteSale() {

    }*/
}