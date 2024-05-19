package model;

public class FlatRateDiscount implements DiscountStrategy {

    float priceReduction;

    public FlatRateDiscount(float priceReduction) {
        this.priceReduction = priceReduction;
    }

    @Override
    public float calculatePriceReduction(float amount) {
        return priceReduction;
    }
}
