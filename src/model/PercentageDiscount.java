package model;

public class PercentageDiscount implements DiscountStrategy {

    private int percentage;

    public PercentageDiscount(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public float calculatePriceReduction(float amount) {
        return amount * (percentage / 100f);
    }
}
