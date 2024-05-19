package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.utilities.OutputHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements RevenueObserver {
    private float totalRevenue = 0;
    private PrintWriter printWriter;

    /**
     * Constructor to create the TotalRevenueFileOutput.
     * Initializes the printWriter.
     */
    public TotalRevenueFileOutput() {
        try {
            printWriter = new PrintWriter(new FileWriter("revenue_output.txt"), true);
        }
        catch (IOException e) {
            System.out.println("Failed to create file for revenue output.");
        }
    }

    /**
     * Prints the current revenue to the file.
     */
    private void printCurrentState() {
        printWriter.println(String.format("TotalRevenueView: Current revenue is %S\n", OutputHelper.getFloatWithColon(totalRevenue)));
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
