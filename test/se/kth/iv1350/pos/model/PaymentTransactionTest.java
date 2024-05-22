package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTransactionTest {

    private DiscountRegister discountRegister;
    private ExternalInventorySystem externalInventorySystem;
    private Sale sale;
    private Register register;
    private Printer printer;
    private PaymentTransaction paymentTransaction;

    @BeforeEach
    void setUp() throws ItemNotFoundException{
        discountRegister = DiscountRegister.getDiscountRegister();
        externalInventorySystem = ExternalInventorySystem.getExternalInventorySystem();

        sale = new Sale(discountRegister, externalInventorySystem);
        sale.addItemID("abc123", 10);

        printer = new Printer();
        register = new Register();
        paymentTransaction = new PaymentTransaction(sale, register, printer);
    }

    @Test
    void testAddPayment() {

        paymentTransaction.addPayment(100f);
        assertEquals(216.94f, paymentTransaction.getAmountLeft(),"Should be 216.94 left to pay.");

        paymentTransaction.addPayment(100f);
        assertEquals(200f, paymentTransaction.getAmountPaid(),"200 should have been paid in total now.");

        paymentTransaction.addPayment(200f);
        assertEquals(83.06f, paymentTransaction.getChange(), "83.06 should be given in change.");
    }
}