package integration;

public class Register {
    private float amountInside;

    /**
     * Creates a new instance of the Register class.
     * This class instance represents a virtual version of the external register.
     */
    public Register() {
        amountInside = 0;
    }

    /**
     * Deposits a certain amount of money into the register.
     * 
     * @param amount The amount to deposit.
     */
    public void depositAmount(float amount) {
        amountInside += amount;
    }

    /**
     * Withdraws a certain amount of money from the register.
     * 
     * @param amount The amount to withdraw.
     */
    public void withdrawAmount(float amount) {
        amountInside -= amount;
    }

    public float getAmountInside() {
        return amountInside;
    }
}
