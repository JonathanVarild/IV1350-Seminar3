package controller;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import integration.Register;
import model.TotalRevenueFileOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TotalRevenueView;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        Register register = new Register();
        Printer printer = new Printer();
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        Controller controller = new Controller(ExternalInventorySystem.getExternalInventorySystem(), ExternalAccountingSystem.getExternalAccountingSystem(), register, printer, totalRevenueView, totalRevenueFileOutput);
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