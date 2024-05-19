package se.kth.iv1350.pos.model;

public interface RevenueObserver {

    /**
     * Invoked when a sale has been completed.
     *
     * @param amount The amount of money that was earned.
     */
    void addRevenue(float amount);

}
