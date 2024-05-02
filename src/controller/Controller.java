package controller;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import integration.Register;
import model.Item;
import model.PaymentTransaction;
import model.Sale;

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

    public void endSale() {
        float runningTotal = sale.getRunningTotal();
        float totalVAT = sale.getTotalVAT();

        transaction = new PaymentTransaction(sale, register, printer);
    }

    public String enterItemID(String itemID, int quantity) {
        Item item = sale.addItemID(itemID, quantity);

        if (item != null) {
            return String.format("Item ID: %s\nItem name: %s\nItem cost: %f SEK\nVAT: %s\nItem description: %s\n\nTotal cost (incl VAT): %f SEK\nTotal VAT: ‰f\n\n",item.itemID, item.name, item.price, item.vatRate, item.description, sale.getRunningTotal() + sale.getTotalVAT(), sale.getTotalVAT());
        }

        return "";
    }

    public void enterAmount(float amount) {
        transaction.addPayment(amount);

        accountingSystem.saveTransaction(transaction);

        sale.completeSale();
    }

    public void discountRequest(int customerID) {

    }

}
