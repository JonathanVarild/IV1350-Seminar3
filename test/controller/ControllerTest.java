package controller;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import integration.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        Register register = new Register();
        Printer printer = new Printer();
        Controller controller = new Controller(inventorySystem, accountingSystem, register, printer);
    }

    @Test
    void startSaleTest() {

    }

    @Test
    void endSaleTest() {
    }

    @Test
    void enterItemIDTest() {
    }

    @Test
    void enterAmountTest() {
    }

    @Test
    void discountRequestTest() {
    }
}