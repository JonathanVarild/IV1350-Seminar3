package se.kth.iv1350.pos.model;

/**
 * Class that extends DiscountStrategy to make use of Strategy Pattern.
 * Allows creation of discounts with a percentage.
 */
public class PercentageDiscount implements DiscountStrategy {

    private int percentage;

    /**
     * Constructor for the PercentageDiscount.
     *
     * @param percentage The percentage to be reducted from the amount.
     */
    public PercentageDiscount(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Method used to calculate the price to be reduced.
     *
     * @param amount The amount to base the price reduction on.
     *
     * @return The amount to be reduced from the price.
     */
    @Override
    public float calculatePriceReduction(float amount) {
        return amount * (percentage / 100f);
    }
}
