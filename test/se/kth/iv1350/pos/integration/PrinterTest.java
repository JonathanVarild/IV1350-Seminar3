package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    Printer printer;
    Sale sale;

    @BeforeEach
    void setUp() {
        printer = new Printer();

        DiscountRegister discountRegister = DiscountRegister.getDiscountRegister();
        ExternalInventorySystem externalInventorySystem = ExternalInventorySystem.getExternalInventorySystem();

        sale = new Sale(discountRegister, externalInventorySystem);
    }

    @Test
    void printReceipt() throws ItemNotFoundException, DatabaseUnavailableException {

        sale.addItemID("TEST_ITEM", 5);
        sale.applyDiscounts("TEST_CUSTOMER");

        String receipt = printer.printReceipt(sale);

        assertTrue(receipt.contains("Begin receipt"), "Receipt should include receipt header.");
        assertTrue(receipt.matches("(?s).*Time of sale: \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.*"), "Receipt should include the formatted time of sale.");
        assertTrue(receipt.matches("(?s).*Total discount: \\d+:\\d+ SEK.*"), "Receipt should include the formatted total discount.");
        assertTrue(receipt.matches("(?s).*\\w+\\s+\\d+ x \\d+:\\d+\\t+\\d+:\\d+.*"), "Receipt should include the formatted item details.");
    }
}