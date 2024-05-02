package utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputHelperTest {

    @Test
    void testGetFloatWithColon_Negatives() {
        String result = OutputHelper.getFloatWithColon(-123.456f);
        assertEquals("-123:46", result, "Should handle negative values correctly with negative integer and positive decimal after colon.");
    }

    @Test
    void testGetFloatWithColon_1Decimal() {
        String result = OutputHelper.getFloatWithColon(123.4f);
        assertEquals("123:40", result, "Should format one decimal by adding a zero to the end.");
    }

    @Test
    void testGetFloatWithColon_2Decimals() {
        String result = OutputHelper.getFloatWithColon(123.45f);
        assertEquals("123:45", result, "Should format 2 decimal numbers without losing accuracy.");
    }

    @Test
    void testGetFloatWithColon_3Decimals() {
        String result = OutputHelper.getFloatWithColon(123.456f);
        assertEquals("123:46", result, "Should format float number with 2 decimals and separating colon.");
    }

    @Test
    void testGetFloatWithColon_RoundUp() {
        String result = OutputHelper.getFloatWithColon(123.4567f);
        assertEquals("123:46", result, "Should round up to the nearest number up for 2 decimals.");
    }

    @Test
    void testGetFloatWithColon_RoundDown() {
        String result = OutputHelper.getFloatWithColon(123.454f);
        assertEquals("123:45", result, "Should round down to the nearest number down for 2 decimals.");
    }

    @Test
    void testGetFloatWithColon_Zero() {
        String result = OutputHelper.getFloatWithColon(0f);
        assertEquals("0:0", result, "Should handle float numbers that are zero.");
    }


}