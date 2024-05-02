package model;

public class PaymentTransaction {

    private Sale sale;
    private float toPay = 0;
    private float paid = 0;
    private float change = 0;

    public PaymentTransaction(Sale sale) {
        this.sale = sale;
    }

    public void addPayment(float amount) {

    }

    private void calculateSums() {

    }

    public float getAmountLeft() {
        return toPay;
    }

    public float getAmountPaid() {
        return paid;
    }

    public float getChange() {
        return change;
    }

}
