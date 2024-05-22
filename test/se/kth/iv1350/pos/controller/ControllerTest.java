package se.kth.iv1350.pos.controller;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.PaymentTransaction;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.TotalRevenueFileOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.utilities.ErrorLogger;
import se.kth.iv1350.pos.view.TotalRevenueView;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        ErrorLogger.setupLoggingSystem("test_errors.txt");
        Register register = new Register();
        Printer printer = new Printer();
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        controller = new Controller(DiscountRegister.getDiscountRegister(), ExternalInventorySystem.getExternalInventorySystem(), ExternalAccountingSystem.getExternalAccountingSystem(), register, printer, totalRevenueView, totalRevenueFileOutput);
    }

    @Test
    void startSaleTest() {
        Sale sale = controller.startSale();
        assertNotNull(sale, "Sale object should not be null after starting new sale.");
        assertNull(controller.getTransaction(), "Transaction should be null after starting new sale.");
    }

    @Test
    void endSaleTest() {
        Sale sale = controller.startSale();
        PaymentTransaction transaction = controller.endSale();
        assertNotNull(transaction, "Transaction object should not be null after ending sale.");
        assertEquals(sale.getRunningTotal() + sale.getTotalVAT(), transaction.getAmountLeft(), "Sale running total and total VAT should be passed to PaymentTransaction as sum.");
    }

    @Test
    void enterItemIDTestValidItem() {
        controller.startSale();

        try {
            Item item = controller.enterItemID("abc123", 4);
            assertNotNull(item, "EnterItemID should return Item object for 'abc123'.");
        }
        catch (ItemNotFoundException e) {
            fail("Item 'abc123' should not throw ItemNotFoundException.");
        }
    }

    @Test
    void enterItemIDTestInvalidItem() {
        controller.startSale();

        try {
            controller.enterItemID("INVALID_ITEM_ID", 1);
            fail("EnterItemID should throw ItemNotFoundException for invalid item.");
        }
        catch (ItemNotFoundException e) {
            assertEquals(e.getItemID(), "INVALID_ITEM_ID", "Exception getItemID() should return 'INVALID_ITEM_ID'.");
        }
    }

    @Test
    void enterAmountTest() throws ItemNotFoundException {
        controller.startSale();
        controller.enterItemID("abc123", 4);
        PaymentTransaction transaction = controller.endSale();
        float toPay = transaction.getAmountLeft();
        float payAmount = 20;
        controller.enterAmount(payAmount);
        assertEquals(toPay-payAmount, transaction.getAmountLeft(), String.format("Amount left of transaction should decrease with %f", toPay));
    }

    @Test
    void discountRequestTest() throws ItemNotFoundException {
        Sale sale = controller.startSale();
        controller.enterItemID("abc123", 4);
        float runningTotal = sale.getRunningTotal();
        float totalVAT = sale.getTotalVAT();
        controller.discountRequest("TEST_CUSTOMER");

        assertNotEquals(runningTotal, sale.getTotalVAT(), "Running total should not be equal after applying test discounts.");
        assertNotEquals(totalVAT, sale.getTotalVAT(), "Total VAT should not be equal after applying test discounts.");
    }
}