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

    public Controller(ExternalInventorySystem inventorySystem, ExternalAccountingSystem accountingSystem, Register register, Printer printer) {
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.register = register;
        this.printer = printer;
    }

    public void startSale() {
        sale = new Sale(inventorySystem);
        transaction = null;
    }

    public String endSale() {
        float runningTotal = sale.getRunningTotal();
        float totalVAT = sale.getTotalVAT();

        transaction = new PaymentTransaction(sale, register, printer);

        return String.format("End sale:\nTotal cost (incl VAT): %s\n", OutputHelper.getFloatWithColon(runningTotal + totalVAT));
    }

    public String enterItemID(String itemID, int quantity) {
        Item item = sale.addItemID(itemID, quantity);

        if (item != null) {
            return String.format("Item ID: %s\nItem name: %s\nItem cost: %s SEK\nVAT: %s%%\nItem description: %s\n\nTotal cost (incl VAT): %s SEK\nTotal VAT: %s SEK\n",item.itemID, item.name, OutputHelper.getFloatWithColon(item.price), item.vatRate, item.description, OutputHelper.getFloatWithColon(sale.getRunningTotal() + sale.getTotalVAT()), OutputHelper.getFloatWithColon(sale.getTotalVAT()));
        }

        return "";
    }

    public String enterAmount(float amount) {
        String receiptData = transaction.addPayment(amount);

        accountingSystem.saveTransaction(transaction);

        String actionsLog = sale.completeSale();

        return String.format("Customer pays %s SEK:\nSent sale info to external accounting system.\n%s\n%s", OutputHelper.getFloatWithColon(amount), actionsLog, receiptData);
    }

    public void discountRequest(int customerID) {

    }

}
