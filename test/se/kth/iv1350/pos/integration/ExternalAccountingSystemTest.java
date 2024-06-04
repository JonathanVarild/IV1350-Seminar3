package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.PaymentTransaction;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ExternalAccountingSystemTest {

    ExternalAccountingSystem externalAccountingSystem;

    @BeforeEach
    void setUp() {
        externalAccountingSystem = ExternalAccountingSystem.getExternalAccountingSystem();
    }

    @Test
    void saveTransactionTest() {
        ExternalInventorySystem externalInventorySystem = ExternalInventorySystem.getExternalInventorySystem();
        Register register = new Register();

        Sale sale = new Sale(DiscountRegister.getDiscountRegister(), externalInventorySystem);
        PaymentTransaction transaction = new PaymentTransaction(sale, register, new Printer());

        int numOfTransactions = externalAccountingSystem.getTransactions().length;

        externalAccountingSystem.saveTransaction(transaction);

        assertEquals(numOfTransactions + 1, externalAccountingSystem.getTransactions().length, "Number of saved transactions should be incremented by 1.");
    }

    @Test
    void getExternalAccountingSystemTest() {
        assertNotNull(ExternalInventorySystem.getExternalInventorySystem(), "Method getExternalInventorySystem should always return a object instance.");
    }
}