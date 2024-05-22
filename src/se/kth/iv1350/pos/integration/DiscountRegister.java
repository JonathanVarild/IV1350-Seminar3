package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Discount;
import se.kth.iv1350.pos.model.FlatRateDiscount;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.PercentageDiscount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscountRegister {

    private static final DiscountRegister DISCOUNT_REGISTER = new DiscountRegister();

    private HashMap<String, Discount> customerDiscounts = new HashMap<>();
    private List<Discount> globalDiscounts = new ArrayList<>();

    /**
     * Creates a new instance of the DiscountRegister class.
     * This class instance represents a virtual version of the external discount register.
     */
    private DiscountRegister() {
        customerDiscounts.put("TEST_CUSTOMER", new Discount(new PercentageDiscount(20), "abc123"));
        customerDiscounts.put("Customer_1111", new Discount(new FlatRateDiscount(20)));
        customerDiscounts.put("Customer_2222", new Discount(new PercentageDiscount(20)));
        customerDiscounts.put("Customer_3333", new Discount(new PercentageDiscount(10), "abc123"));

        globalDiscounts.add(new Discount(new FlatRateDiscount(10f), "TEST_ITEM"));
        globalDiscounts.add(new Discount(new FlatRateDiscount(4.9f), "def456"));
    }

    /**
     * Getter to return singleton of Discount Register.
     *
     * @return The single DiscountRegister instance available.
     */
    public static DiscountRegister getDiscountRegister() {
        return DISCOUNT_REGISTER;
    }

    /**
     * Gets discounts for a specific customer based on their items.
     * 
     * @param customerID The customer ID.
     * @param items The items to get the discounts for.
     * 
     * @return The discounts for the customer and items.
     */
    public Discount[] getDiscounts(String customerID, Item[] items) {
        List<Discount> discounts = new ArrayList<>();

        if (customerDiscounts.containsKey(customerID)) {
            discounts.add(customerDiscounts.get(customerID));
        }

        for (Discount discount : globalDiscounts) {
            for (Item item : items) {
                if (discount.getItemID() == null || discount.getItemID().equals(item.itemID)) {
                    discounts.add(discount);
                    break;
                }
            }
        }

        return discounts.toArray(new Discount[0]);
    }
}
