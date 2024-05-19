package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.model.TotalRevenueFileOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.view.TotalRevenueView;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        Register register = new Register();
        Printer printer = new Printer();
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        Controller controller = new Controller(DiscountRegister.getDiscountRegister(), ExternalInventorySystem.getExternalInventorySystem(), ExternalAccountingSystem.getExternalAccountingSystem(), register, printer, totalRevenueView, totalRevenueFileOutput);
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