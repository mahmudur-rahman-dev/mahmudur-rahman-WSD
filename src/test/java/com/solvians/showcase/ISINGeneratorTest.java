package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISINGeneratorTest {

    private final ISINGenerator generator = new ISINGenerator();

    @Test
    void testGenerateISIN() {
        var isin = generator.generateISIN();

        assertEquals(
                12, isin.length(), "ISIN should be 12 characters long"
        );
        assertTrue(
                Character.isUpperCase(isin.charAt(0)) && Character.isUpperCase(isin.charAt(1)),
                "First two characters should be uppercase letters"
        );
        assertTrue(
                isin.substring(2, 11).matches("[A-Z0-9]+"),
                "Characters 3-11 should be alphanumeric"
        );
        assertTrue(
                Character.isDigit(isin.charAt(11)),
                "Last character should be a digit"
        );
    }

    @Test
    void testUniqueGeneration() {
        var isin1 = generator.generateISIN();
        var isin2 = generator.generateISIN();
        assertNotEquals(isin1, isin2, "Generated ISINs should be unique");
    }
}