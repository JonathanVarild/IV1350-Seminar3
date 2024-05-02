package integration;

import model.PaymentTransaction;

import java.util.ArrayList;

public class ExternalAccountingSystem {

    private ArrayList<PaymentTransaction> transactions;

    /**
     * Creates a new instance of the ExternalAccountingSystem class.
     * This class instance represents a virtual version of the external accounting system.
     */
    public ExternalAccountingSystem() {
        transactions = new ArrayList<>();
    }

    /**
     * Saves a payment transaction to the external accounting system.
     * 
     * @param transaction The payment transaction to save.
     */
    public void saveTransaction(PaymentTransaction transaction) {
        transactions.add(transaction);
    }
}
