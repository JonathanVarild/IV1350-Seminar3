package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.PaymentTransaction;

import java.util.ArrayList;

public class ExternalAccountingSystem {

    private static ExternalAccountingSystem EXTERNAL_ACCOUNTING_SYSTEM = new ExternalAccountingSystem();

    private ArrayList<PaymentTransaction> transactions;

    /**
     * Creates a new instance of the ExternalAccountingSystem class.
     * This class instance represents a virtual version of the external accounting system.
     */
    private ExternalAccountingSystem() {
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

    /**
     * Getter to return singleton of External Accounting System.
     *
     * @return The single ExternalAccountingSystem instance available.
     */
    public static ExternalAccountingSystem getExternalAccountingSystem() {
        return EXTERNAL_ACCOUNTING_SYSTEM;
    }

    /**
     * Getter used to get the transactions list as an array.
     *
     * @return All transactions as an array.
     */
    public PaymentTransaction[] getTransactions() {
        return transactions.toArray(new PaymentTransaction[0]);
    }
}
