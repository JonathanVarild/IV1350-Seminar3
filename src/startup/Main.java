package startup;

import controller.Controller;
import integration.DiscountRegister;
import integration.ExternalAccountingSystem;
import integration.Printer;
import integration.Register;
import view.View;

public class Main {

    public static void main(String[] args) {
        final DiscountRegister discountRegister = new DiscountRegister();
        final ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        final Register register = new Register();
        final ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
        final Printer printer = new Printer();
        final View view = new View();
        final Controller controller = new Controller();
    }
}
