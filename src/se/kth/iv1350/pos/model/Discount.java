package se.kth.iv1350.pos.model;

public class Discount {

    private DiscountStrategy discountStrategy;
    private String appliesToItemID;

    /**
     * Create a new instance of the Discount class without specifying an itemID.
     *
     * @param discountStrategy The discount strategy used for the discount.
     */
    public Discount(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Creates a new instance of the Discount class and also specify an itemID.
     * The discount class represents a discount that can be applied to an item.
     * 
     * @param appliesToItemID The item ID that the discount applies to.
     * @param discountStrategy The discount strategy used for the discount.
     */
    public Discount(DiscountStrategy discountStrategy, String appliesToItemID) {
        this.discountStrategy = discountStrategy;
        this.appliesToItemID = appliesToItemID;
    }

    /**
     * Gets the item ID that the discount applies to.
     * 
     * @return The item ID.
     */
    public String getItemID() {
        return appliesToItemID;
    }

    /**
     * Used to calculate the price reduction for the discount.
     *
     * @param totalAmount The total amount of the cart.
     *
     * @return The amount to be redacted from the total cost.
     */
    public float getTotalPriceReduction(float totalAmount) {
        if (appliesToItemID == null) {
            return discountStrategy.calculatePriceReduction(totalAmount);
        }
        return 0;
    }

    /**
     * Used to calculate the price reduction for the discount.
     *
     * @param item The item to reduce the cost from.
     *
     * @return The amount to be redacted from the total cost.
     */
    public float getItemPriceReduction(Item item) {
        if (appliesToItemID != null) {
            return discountStrategy.calculatePriceReduction(item.price);
        }
        return 0;
    }
}
