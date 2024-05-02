package controller;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import integration.Register;
import model.Item;
import model.PaymentTransaction;
import model.Sale;
import utilities.OutputHelper;

public class Controller {

    private ExternalInventorySystem inventorySystem;
    private ExternalAccountingSystem accountingSystem;
    private Register register;
    private Printer printer;

    private Sale sale;
    private PaymentTransaction transaction;

    /**
     * Creates a new instance of the Controller class.
     * 
     * @param inventorySystem The external inventory system.
     * @param accountingSystem The external accounting system.
     * @param register The register.
     * @param printer The printer.
     */

    public Controller(ExternalInventorySystem inventorySystem, ExternalAccountingSystem accountingSystem, Register register, Printer printer) {
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.register = register;
        this.printer = printer;
    }


    /**
     * Starts a new sale by creating a new instance of the Sale class.
     * Resets the transaction to null to ensure that no previous transaction is still active.
     */
    public void startSale() {
        sale = new Sale(inventorySystem);
        transaction = null;
    }


    /**
     * Ends the current sale and creates a new instance of the PaymentTransaction class.
     * 
     * @return Output string for view with the total cost of the sale.
     */
    public String endSale() {
        float runningTotal = sale.getRunningTotal();
        float totalVAT = sale.getTotalVAT();

        transaction = new PaymentTransaction(sale, register, printer);

        return String.format("End sale:\nTotal cost (incl VAT): %s\n", OutputHelper.getFloatWithColon(runningTotal + totalVAT));
    }

    /**
     * Used by view to enter an itemID and quantity to add to the sale.
     * 
     * @param itemID The itemID of the item to add.
     * @param quantity The quantity of the item to add.
     * 
     * @return Output string for view with information related to the item added to the sale.
     */
    public String enterItemID(String itemID, int quantity) {
        Item item = sale.addItemID(itemID, quantity);

        if (item != null) {
            return String.format("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n\nTotal cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description, OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }

        return "";
    }

    /**
     * Used by view to enter the amount paid by the customer.
     * 
     * @param amount The amount paid by the customer.
     * 
     * @return Output string for view with information related to the payment, external inventory system and receipt.
     */
    public String enterAmount(float amount) {
        String receiptData = transaction.addPayment(amount);

        accountingSystem.saveTransaction(transaction);

        String actionsLog = sale.completeSale();

        return String.format("Customer pays %s SEK:\nSent sale info to external accounting system.\n%s\n%s", OutputHelper.getFloatWithColon(amount), actionsLog, receiptData);
    }

    /**
     * Used by view to request a discount for a specific customer by their customerID.
     * 
     * @param customerID The customerID of the customer requesting the discount.
     */
    public void discountRequest(int customerID) {

    }

}
