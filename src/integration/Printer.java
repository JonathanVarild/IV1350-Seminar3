package integration;

import model.Item;
import model.PaymentTransaction;
import model.Sale;
import utilities.OutputHelper;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Printer {
    public String printReceipt(PaymentTransaction transaction, Sale sale) {

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
