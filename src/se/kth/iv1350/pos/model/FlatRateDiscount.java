package se.kth.iv1350.pos.model;

/**
 * Class that extends DiscountStrategy to make use of Strategy Pattern.
 * Allows creation of discounts with a flat rate.
 */
public class FlatRateDiscount implements DiscountStrategy {

    private float priceReduction;

    /**
     * Constructor for FlatRateDiscount.
     *
     * @param priceReduction The flat rate price reduction.
     */
    public FlatRateDiscount(float priceReduction) {
        this.priceReduction = priceReduction;
    }

    /**
     * Method for calculation the price reduction based on the amount.
     * Since the reduction is flat rate, amount variable is not directly used.
     *
     * @param amount The amount to base the price reduction on. (not used)
     *
     * @return The amount to be reduced from the price.
     */
    @Override
    public float calculatePriceReduction(float amount) {
        return priceReduction;
    }
}
