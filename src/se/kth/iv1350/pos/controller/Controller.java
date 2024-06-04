package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;
import se.kth.iv1350.pos.view.TotalRevenueView;

public class Controller {

    private DiscountRegister discountRegister;
    private ExternalInventorySystem inventorySystem;
    private ExternalAccountingSystem accountingSystem;
    private Register register;
    private Printer printer;

    private TotalRevenueView totalRevenueView;
    private TotalRevenueFileOutput totalRevenueFileOutput;

    private Sale sale;
    private PaymentTransaction transaction;

    /**
     * Creates a new instance of the Controller class.
     *
     * @param discountRegister The discount register.
     * @param inventorySystem The inventory system.
     * @param accountingSystem The accounting system.
     * @param register The cash register.
     * @param printer The receipt printer.
     * @param totalRevenueView The total revenue view.
     * @param totalRevenueFileOutput The total revenue file output.
     */
    public Controller(DiscountRegister discountRegister, ExternalInventorySystem inventorySystem, ExternalAccountingSystem accountingSystem, Register register, Printer printer, TotalRevenueView totalRevenueView, TotalRevenueFileOutput totalRevenueFileOutput) {
        this.discountRegister = discountRegister;
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.register = register;
        this.printer = printer;
        this.totalRevenueView = totalRevenueView;
        this.totalRevenueFileOutput = totalRevenueFileOutput;
    }


    /**
     * Starts a new sale by creating a new instance of the Sale class.
     * Resets the transaction to null to ensure that no previous transaction is still active.
     */
    public Sale startSale() {
        sale = new Sale(discountRegister, inventorySystem);
        transaction = null;
        return sale;
    }


    /**
     * Ends the current sale and creates a new instance of the PaymentTransaction class.
     * 
     * @return Output string for view with the total cost of the sale.
     */
    public PaymentTransaction endSale() {
        transaction = new PaymentTransaction(sale, register, printer);
        transaction.addRevenueObserver(totalRevenueView);
        transaction.addRevenueObserver(totalRevenueFileOutput);
        return transaction;
    }

    /**
     * Used by view to enter an itemID and quantity to add to the sale.
     * 
     * @param itemID The itemID of the item to add.
     * @param quantity The quantity of the item to add.
     * 
     * @return Output string for view with information related to the item added to the sale.
     *
     * @throws ItemNotFoundException the itemID couldn't be found.
     * @throws DatabaseUnavailableException the database could not be contacted.
     */
    public Item enterItemID(String itemID, int quantity) throws ItemNotFoundException, DatabaseUnavailableException {
        return sale.addItemID(itemID, quantity);
    }

    /**
     * Used by view to enter the amount paid by the customer.
     * 
     * @param amount The amount paid by the customer.
     *
     * @throws ItemNotFoundException if a scanned item cannot be found in the inventory system.
     * @throws DatabaseUnavailableException the database could not be contacted.
     */
    public void enterAmount(float amount) throws ItemNotFoundException, DatabaseUnavailableException {
        sale.completeSale();
        transaction.addPayment(amount);
        accountingSystem.saveTransaction(transaction);
    }

    /**
     * Used by view to request a discount for a specific customer by their customerID.
     * 
     * @param customerID The customerID of the customer requesting the discount.
     */
    public Discount[] discountRequest(String customerID) {
        return sale.applyDiscounts(customerID);
    }

    /**
     * Method used to get the current transaction.
     *
     * @return The current PaymentTransaction.
     */
    public PaymentTransaction getTransaction() {
        return transaction;
    }
}
