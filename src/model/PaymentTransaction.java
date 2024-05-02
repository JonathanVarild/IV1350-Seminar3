package model;

import integration.Printer;
import integration.Register;

public class PaymentTransaction {

    private Register register;
    private Printer printer;

    private Sale sale;
    private float toPay;
    private float paid = 0;

    public PaymentTransaction(Sale sale, Register register, Printer printer) {
        this.sale = sale;
        this.register = register;
        this.printer = printer;

        toPay = sale.getRunningTotal() + sale.getTotalVAT();
    }

    public void addPayment(float amount) {
        paid += amount;

        printer.printReceipt(this, sale);

        register.depositAmount(amount);
        register.withdrawAmount(getChange());
    }

    public float getAmountLeft() {
        return toPay - paid;
    }

    public float getAmountPaid() {
        return paid;
    }

    public float getChange() {
        return paid - toPay;
    }

}
