package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link Tile} interface, representing a unit from the circle
 */
public class CircleTile implements Tile {
    final int value;
    private final int previous;
    private final int next;

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

        this.previous = value == 1 ? maxValue : value - 1;
        this.next = value == maxValue ? 1 : value + 1;

        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isNext(@NotNull Tile next) {
        return this.next == next.getValue();
    }

    @Override
    public boolean isPrevious(@NotNull Tile previous){
        return this.previous == previous.getValue();
    }

    @Override
    public String toString() {
        return "Tile{" +
                "value=" + value +
                '}';
    }
}
