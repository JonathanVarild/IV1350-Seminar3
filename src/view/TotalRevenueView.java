package view;

import model.RevenueObserver;
import utilities.OutputHelper;

public class TotalRevenueView implements RevenueObserver {

    private float totalRevenue = 0;

    private void printCurrentState() {
        System.out.printf("TotalRevenueView: Current revenue is %S\n", OutputHelper.getFloatWithColon(totalRevenue));
    }

    @Override
    public void addRevenue(float amount) {
        totalRevenue += amount;
        printCurrentState();
    }
}


