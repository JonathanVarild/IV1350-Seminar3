package model;

public class Discount {

    private int appliesToItemID;
    private float sumToBeReduced;
    private int percentageToBeReduced;

    /**
     * Creates a new instance of the Discount class.
     * The discount class represents a discount that can be applied to an item.
     * 
     * @param appliesToItemID The item ID that the discount applies to.
     * @param sumToBeReduced The sum to be reduced.
     * @param percentageToBeReduced The percentage to be reduced.
     */
    public Discount(int appliesToItemID, float sumToBeReduced, int percentageToBeReduced) {
        this.appliesToItemID = appliesToItemID;
        this.sumToBeReduced = sumToBeReduced;
        this.percentageToBeReduced = percentageToBeReduced;
    }

    /**
     * Gets the item ID that the discount applies to.
     * 
     * @return The item ID.
     */
    public int getItemID() {
        return appliesToItemID;
    }

    /**
     * Gets the sum to be reduced from the item price.
     * 
     * @return The sum to be reduced.
     */
    public float getSumReduction() {
        return sumToBeReduced;
    }

    /**
     * Gets the percentage to be reduced from the item price.
     * 
     * @return The percentage to be reduced.
     */
    public int getPercentageReduction() {
        return percentageToBeReduced;
    }
}
