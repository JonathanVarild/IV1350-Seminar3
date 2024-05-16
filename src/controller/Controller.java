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
    public Sale startSale() {
        sale = new Sale(inventorySystem);
        transaction = null;
        return sale;
    }


    /**
     * Ends the current sale and creates a new instance of the PaymentTransaction class.
     * 
     * @return Output string for view with the total cost of the sale.
     */
    public PaymentTransaction endSale() {
        float runningTotal = sale.getRunningTotal();
        float totalVAT = sale.getTotalVAT();

        transaction = new PaymentTransaction(sale, register, printer);
        return transaction;
    }

    /**
     * Used by view to enter an itemID and quantity to add to the sale.
     * 
     * @param itemID The itemID of the item to add.
     * @param quantity The quantity of the item to add.
     * 
     * @return Output string for view with information related to the item added to the sale.
     */
    public Item enterItemID(String itemID, int quantity) {
        return sale.addItemID(itemID, quantity);
    }

    /**
     * Used by view to enter the amount paid by the customer.
     * 
     * @param amount The amount paid by the customer.
     */
    public void enterAmount(float amount) {
        transaction.addPayment(amount);
        accountingSystem.saveTransaction(transaction);
        sale.completeSale();

    }

    /**
     * Used by view to request a discount for a specific customer by their customerID.
     * 
     * @param customerID The customerID of the customer requesting the discount.
     */
    public void discountRequest(int customerID) {

    }

}
