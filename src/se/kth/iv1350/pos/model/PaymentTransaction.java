package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.Register;

import java.util.ArrayList;
import java.util.List;

public class PaymentTransaction {

    private Register register;
    private Printer printer;
    private String receiptString;

    private Sale sale;
    private float toPay;
    private float paid = 0;

    private List<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates a new instance of the PaymentTransaction class.
     * 
     * @param sale The sale to pay for.
     * @param register The register.
     * @param printer The printer.
     */
    public PaymentTransaction(Sale sale, Register register, Printer printer) {
        this.sale = sale;
        this.register = register;
        this.printer = printer;

        toPay = sale.getRunningTotal() + sale.getTotalVAT();
    }

    /**
     * Adds a payment to the transaction.
     * 
     * @param amount The amount that was paid.
     */
    public void addPayment(float amount) {
        paid += amount;

        register.depositAmount(amount);
        register.withdrawAmount(getChange());

        receiptString = printer.printReceipt(sale);

        notifyObservers();
    }

    /**
     * Returns the amount left to pay.
     * 
     * @return The amount left to pay.
     */
    public float getAmountLeft() {
        return toPay - paid;
    }

    /**
     * Returns the amount that has been paid.
     * 
     * @return The amount that has been paid.
     */
    public float getAmountPaid() {
        return paid;
    }

    /**
     * Returns the change that should be given to the customer.
     * 
     * @return The change that should be given.
     */
    public float getChange() {
        return paid - toPay;
    }

    /**
     * Used to get the string to be printed on the receipt.
     *
     * @return String of receipt.
     */
    public String getReceiptString() {
        return receiptString;
    }

    /**
     * Used to add a new RevenueObserver to the class.
     *
     * @param obs RevenueObserver to be added.
     */
    public void addRevenueObserver(RevenueObserver obs) {
        revenueObservers.add(obs);
    }

    /**
     * Used to notify the revenueObservers of a change.
     */
    private void notifyObservers() {
        for (RevenueObserver obs : revenueObservers) {
            obs.addRevenue(toPay);
        }
    }

}
