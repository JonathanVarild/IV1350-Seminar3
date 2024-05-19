package startup;

import controller.Controller;
import integration.*;
import model.TotalRevenueFileOutput;
import utilities.ErrorLogger;
import view.TotalRevenueView;
import view.View;

public class Main {

    /**
     * The main method which starts the applicationn.
     * 
     * @param args Arguments for the application.
     */
    public static void main(String[] args) {
        ErrorLogger.setupLoggingSystem("errors.txt");

        final ExternalAccountingSystem accountingSystem = ExternalAccountingSystem.getExternalAccountingSystem();
        final ExternalInventorySystem inventorySystem = ExternalInventorySystem.getExternalInventorySystem();
        final DiscountRegister discountRegister = DiscountRegister.getDiscountRegister();

        final Printer printer = new Printer();
        final Register register = new Register();

        final TotalRevenueView totalRevenueView = new TotalRevenueView();
        final TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        final Controller controller = new Controller(discountRegister, inventorySystem, accountingSystem, register, printer, totalRevenueView, totalRevenueFileOutput);
        final View view = new View(controller);

        view.runProgram();
    }
}
