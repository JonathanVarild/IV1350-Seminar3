package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    Register register;

    @BeforeEach
    void setUp() {
        register = new Register();
    }

    @Test
    void depositAmount() {
        float amountBefore = register.getAmountInside();
        register.depositAmount(123.456f);
        assertEquals(register.getAmountInside(), amountBefore + 123.456f, "Register amount should increase with 123.456 float.");
    }

    @Test
    void withdrawAmount() {
        float amountBefore = register.getAmountInside();
        register.withdrawAmount(123.456f);
        assertEquals(register.getAmountInside(), amountBefore - 123.456f, "Register amount should decrease with 123,456 float.");
    }
}