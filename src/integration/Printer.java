package integration;

import model.Item;
import model.PaymentTransaction;
import model.Sale;
import utilities.OutputHelper;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Printer {

    /**
     * Creates a new instance of the Printer class.
     * This class instance represents a virtual version of the external printer.
     */
    public Printer() {
    }

    /**
     * Virtually prints a receipt for a sale by creating a string with the receipt information and returning it.
     * 
     * @param sale The sale to print a receipt for.
     * 
     * @return The receipt string to print.
     */
    public String printReceipt(Sale sale) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String receiptString = "";

        receiptString += "------------------ Begin receipt -------------------\n";
        receiptString += String.format("Time of sale: %s\n\n", sale.time.format(dateFormat));

        for (Map.Entry<String, Item> entry : sale.getItems().entrySet()) {
            String itemID = entry.getKey();
            Item item = entry.getValue();

            receiptString += String.format("%s\t\t\t%d x %s\t%s\n", item.name, item.quantity, OutputHelper.getFloatWithColon(item.price), OutputHelper.getFloatWithColon(item.price * item.quantity));
        }

        return receiptString;
    }
}
