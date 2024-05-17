package view;

import com.sun.tools.internal.ws.wsdl.document.Output;
import controller.Controller;
import integration.DatabaseUnavailableException;
import integration.ItemNotFoundException;
import model.Item;
import model.PaymentTransaction;
import model.Sale;
import utilities.OutputHelper;

public class View {

    private Controller controller;
    /**
     * Creates a new instance of the View class.
     * The view class represents the user interface for the cashier.
     * 
     * @param controller The controller for the view to interact with.
     */
    public View(Controller controller) {

        this.controller = controller;
    }

    /**
     * Initiates a sale via {@code controller.startSale()} and passes inputs to controller.
     */
    public void runProgram() {
        Sale sale = controller.startSale();

        try {
            System.out.println("Add 1 item with item id abc123:");
            Item item = controller.enterItemID("abc123",1);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
            System.out.printf("ItemID %S could not be found.", e.getItemID());
        }

        try {
            System.out.println("Add 1 item with item id abc123:");
            Item item = controller.enterItemID("abc123",1);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
            System.out.printf("ItemID %S could not be found.", e.getItemID());
        }

        try {
            System.out.println("Add 1 item with item id def456:");
            Item item = controller.enterItemID("def456",1);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
                System.out.printf("ItemID %S could not be found.", e.getItemID());
        }

        PaymentTransaction transaction = controller.endSale();
        System.out.printf("End sale:\nTotal cost (incl VAT): %s\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()));

        try
        {
            float amount = 100;
            controller.enterAmount(amount);
            System.out.printf("Customer pays %S\nSent sale info to external accounting system.%n", OutputHelper.getFloatWithColon(amount));
            System.out.println(sale.getActionsLog());
            System.out.println(transaction.getReceiptString());
        }
        catch (ItemNotFoundException e) {
            System.out.printf("Could not complete sale due to missing itemID (%s).", e.getItemID());
        }
    }
}
