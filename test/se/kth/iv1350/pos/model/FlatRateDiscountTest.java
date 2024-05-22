package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlatRateDiscountTest {

    FlatRateDiscount flatRateDiscount;

    @BeforeEach
    void setUp() {
        flatRateDiscount = new FlatRateDiscount(10);
    }

    @Test
    void calculatePriceReduction() {
        assertEquals(flatRateDiscount.calculatePriceReduction(0), 10, "Method calculatePriceReduction should return 10 SEK for a flat rate discount of 10 SEK.");
    }
}