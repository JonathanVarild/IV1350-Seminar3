package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.DatabaseUnavailableException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.model.Discount;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.PaymentTransaction;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.utilities.OutputHelper;

import javax.xml.crypto.Data;

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
            Item item = controller.enterItemID("abc123",2);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
            System.out.printf("ItemID %s could not be found.\n\n", e.getItemID());
        }
        catch (DatabaseUnavailableException e) {
            System.out.printf("Could not communicate with database %s.\n", e.getDatabaseName());
        }

        try {
            System.out.println("Add 1 item with item id ghi_789:");
            Item item = controller.enterItemID("ghi_789",1);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
            System.out.printf("ItemID %s could not be found.\n\n", e.getItemID());
        }
        catch (DatabaseUnavailableException e) {
            System.out.printf("Could not communicate with database %s.\n", e.getDatabaseName());
        }

        try {
            System.out.println("Add 1 item with item id def456:");
            Item item = controller.enterItemID("def456",1);
            System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n%n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description);
            System.out.printf("Total cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n%n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }
        catch (ItemNotFoundException e) {
                System.out.printf("ItemID %s could not be found.\n\n", e.getItemID());
        }
        catch (DatabaseUnavailableException e) {
            System.out.printf("Could not communicate with database %s.\n", e.getDatabaseName());
        }

        System.out.println("Signaling discount request");
        Discount[] discounts = controller.discountRequest("Customer_1111");
        System.out.printf("Applied %d discounts totalling %S SEK\n", discounts.length, OutputHelper.getFloatWithColon(sale.getTotalDiscount()));

        PaymentTransaction transaction = controller.endSale();
        System.out.printf("\nEnd sale:\nTotal cost (incl VAT): %s\nTotal VAT: %s SEK\n\n", OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));

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
        catch (DatabaseUnavailableException e) {
            System.out.printf("Could not communicate with database %s.\n", e.getDatabaseName());
        }
    }
}
