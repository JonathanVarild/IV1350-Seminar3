package integration;

import model.Discount;
import model.Item;

public class DiscountRegister {

    /**
     * Creates a new instance of the DiscountRegister class.
     * This class instance represents a virtual version of the external discount register.
     */
    public DiscountRegister() {
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
