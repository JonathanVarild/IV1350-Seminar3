package model;

public class Discount {

    private int appliesToItemID;
    private float sumToBeReduced;
    private int percentageToBeReduced;

    public int getItemID() {
        return appliesToItemID;
    }

    public float getSumReduction() {
        return sumToBeReduced;
    }

    public int getPercentageReduction() {
        return percentageToBeReduced;
    }
}
