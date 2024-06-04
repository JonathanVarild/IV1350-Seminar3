package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountTest {

    PercentageDiscount percentageDiscount;

    @BeforeEach
    void setUp() {
        percentageDiscount = new PercentageDiscount(15);
    }

    @Test
    void calculatePriceReduction() {
        assertEquals(percentageDiscount.calculatePriceReduction(200), 30f, 0.01, "Method calculatePriceReduction should return 30 SEK for a percentage rate discount of 15% off 200 SEK.");
    }
}