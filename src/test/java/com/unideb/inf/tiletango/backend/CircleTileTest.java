package com.unideb.inf.tiletango.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CircleTile} class
 */
public class CircleTileTest {
    CircleTile ordinaryTile;
    CircleTile bottomBoundTile;
    CircleTile upperBoundTile;

    @BeforeEach
    void setUp() {
        ordinaryTile = new CircleTile(5, 10);
        bottomBoundTile = new CircleTile(1, 10);
        upperBoundTile = new CircleTile(10, 10);
    }

    @Test
    void getValue() {
        assertEquals(5, ordinaryTile.getValue());
    }

    @Test
    void compareTo() {
        CircleTile nextTile = new CircleTile(6, 10);
        assertEquals(-1, ordinaryTile.compareTo(nextTile));
    }

    @Test
    void compareToUpperBound() {
        assertEquals(-1, upperBoundTile.compareTo(bottomBoundTile));
    }

    @Test
    void constructorInvalidArgumentValueIsLessThanOne() {
        var exception = assertThrows(
                IllegalArgumentException.class, () -> new CircleTile(-1, 10)
        );
        assertEquals("Value must be greater than 0", exception.getMessage());
    }

    @Test
    void constructorInvalidArgumentValueIsLargerThanMaxValue() {
        var exception = assertThrows(
                IllegalArgumentException.class, () -> new CircleTile(10, 5)
        );
        assertEquals("Value must be less than or equals max value", exception.getMessage());
    }
}