package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link Tile} interface, representing a unit from the circle
 */
final class CircleTile implements Tile {
    private final int value;
    private final int helperValue;

    /**
     * Constructor to create {@link CircleTile} class
     * @param value the value of the tile
     * @param maxValue the number of maximum tiles
     * @throws IllegalArgumentException if value is less than 1 or bigger than {@code maxValue}
     */
    public CircleTile(int value, int maxValue) throws IllegalArgumentException {
        if (value < 1) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }
        if (value > maxValue) {
            throw new IllegalArgumentException("Value must be less than or equals max value");
        }

        this.helperValue = value == maxValue ? 0 : value;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Tile o) {
        return helperValue - o.getValue();
    }
}
