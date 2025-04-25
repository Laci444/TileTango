package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

/**
 * Interface which specifies basic behaviour.
 * A {@code Tile} should handle own value and comparison with other {@code Tile}s
 */
public interface Tile {
    /**
     * Gets the value of the tile
     * @return integer value of the tile
     */
    int getValue();

    /**
     * Checks whether the passed tile is the appropriate next
     * @param next {@link Tile} object to check
     * @return {@code true}, if it is the next
     */
    boolean isNext(@NotNull Tile next);

    /**
     * Checks whether the passed tile is the appropriate previous
     * @param previous {@link Tile} object to check
     * @return {@code true}, if it is the previous
     */
    boolean isPrevious(@NotNull Tile previous);
}
