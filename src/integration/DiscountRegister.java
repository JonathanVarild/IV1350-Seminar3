package integration;

import model.Discount;
import model.Item;

public class DiscountRegister {

    private static final DiscountRegister DISCOUNT_REGISTER = new DiscountRegister();

    /**
     * Creates a new instance of the DiscountRegister class.
     * This class instance represents a virtual version of the external discount register.
     */
    private DiscountRegister() {
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
     * Gets the the discounts for a specific customer based on their items.
     * 
     * @param customerID The customer ID.
     * @param items The items to get discounts for.
     * 
     * @return The discounts for the customer and items.
     */
    public Discount[] getDiscounts(int customerID, Item[] items) {
        return new Discount[0];
    }

}
