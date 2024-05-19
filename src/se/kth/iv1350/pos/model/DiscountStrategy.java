package se.kth.iv1350.pos.model;

/**
 * Interface for Strategy Pattern to allow discounts of different types whilst keeping a single method for calculating the price reduction.
 */
public interface DiscountStrategy {

    /**
     * Method to calculate the price reduction based on the amount provided.
     *
     * @param amount The amount to base the price reduction on.
     *
     * @return The price reduction.
     */
    float calculatePriceReduction(float amount);
}
