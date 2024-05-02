package model;

import integration.Printer;
import integration.Register;

public class PaymentTransaction {

    private Register register;
    private Printer printer;

    private Sale sale;
    private float toPay;
    private float paid = 0;

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
     * 
     * @return The receipt data for the sale.
     */
    public String addPayment(float amount) {
        paid += amount;

        register.depositAmount(amount);
        register.withdrawAmount(getChange());

        return printer.printReceipt(sale);
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

}
