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

    public String addPayment(float amount) {
        paid += amount;

        register.depositAmount(amount);
        register.withdrawAmount(getChange());

        return printer.printReceipt(this, sale);
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
