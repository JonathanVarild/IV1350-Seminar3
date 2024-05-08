package startup;

import controller.Controller;
import integration.*;
import view.View;

public class Main {

    /**
     * The main method which starts the applicationn.
     * 
     * @param args Arguments for the application.
     */
    public static void main(String[] args) {
        final DiscountRegister discountRegister = new DiscountRegister();
        final ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        final ExternalInventorySystem inventorySystem = new ExternalInventorySystem();

        final Printer printer = new Printer();
        final Register register = new Register();

        final Controller controller = new Controller(inventorySystem, accountingSystem, register, printer);
        final View view = new View(controller);

        view.runProgram();
    }
}
