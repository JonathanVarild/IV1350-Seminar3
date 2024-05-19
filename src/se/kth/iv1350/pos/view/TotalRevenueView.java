package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.RevenueObserver;
import se.kth.iv1350.pos.utilities.OutputHelper;

public class TotalRevenueView implements RevenueObserver {

    private float totalRevenue = 0;

    /**
     * Prints the total revenue to the View (console).
     */
    private void printCurrentState() {
        System.out.printf("TotalRevenueView: Current revenue is %S\n", OutputHelper.getFloatWithColon(totalRevenue));
    }

    /**
     * Method called by observed class to increment the revenue.
     *
     * @param amount The amount of money that was earned.
     */
    @Override
    public void addRevenue(float amount) {
        totalRevenue += amount;
        printCurrentState();
    }
}


